package com.example.popcorntime.common.presentation.paginating.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcorntime.common.presentation.paginating.domain.DefaultPaginator
import kotlinx.coroutines.launch

class PagingViewModel : ViewModel() {
    var state by mutableStateOf(PaginatorState())
    private val paginator = DefaultPaginator<Int, Any>(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            //getItems(nextPage)
            Result.success(emptyList())
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.message)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                itemsList = state.itemsList + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }

    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextPage()
        }
    }
}

data class PaginatorState(
    val isLoading: Boolean = false,
    val itemsList: List<Any> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)