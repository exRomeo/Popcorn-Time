package com.example.popcorntime.core.uistate

sealed class UIState {
    object Loading : UIState()
    object NotConnected : UIState()
    object Success : UIState()
    class Failure(val message: String) : UIState()
}
