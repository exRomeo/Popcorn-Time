package com.example.popcorntime.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.common.presentation.contract.BaseUIEvent
import com.example.popcorntime.common.presentation.contract.BaseUIState
import com.example.popcorntime.common.presentation.contract.BaseUserEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseMVIViewModel<
        UserEvent : BaseUserEvent,
        UIEvent : BaseUIEvent,
        UIState : BaseUIState
        >() : ViewModel() {

    private val _userEventsFlow: MutableSharedFlow<UserEvent> = MutableSharedFlow()
    val userEvents get() = _userEventsFlow.asSharedFlow()


    private val _uiEventsFlow: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    val uiEvents get() = _uiEventsFlow.asSharedFlow()

    val uiState: UIState by lazy { createInitialUIState() }

    init {
        listenToUserEvents()
    }

    /**
     * use to provide initial ui state
     * */
    abstract fun createInitialUIState(): UIState

    /**
     * use to send events to the ui
     * */
    fun setUIEvent(uiState: UIEvent) {
        viewModelScope.launch {
            _uiEventsFlow.emit(uiState)
        }
    }

    /**
     * use to send events to the view model
     * */
    fun setUserEvent(userEvent: UserEvent) {
        viewModelScope.launch {
            _userEventsFlow.emit(userEvent)
        }
    }


    /**
     * override to handle user events
     * */
    abstract fun handleUserEvent(userEvent: UserEvent)

    private fun listenToUserEvents() {
        viewModelScope.launch {
            userEvents.collect {
                handleUserEvent(it)
            }
        }
    }

    /**
     * override this function to reset the ui state
     * */
    abstract fun resetUIState()
}