package com.example.popcorntime.core.uistate

sealed class UIState {
    object Loading : UIState()
    object NotConnected : UIState()
    class Success<Data>(val data: Data) : UIState()

    class Failure(val message: String) : UIState()
}
