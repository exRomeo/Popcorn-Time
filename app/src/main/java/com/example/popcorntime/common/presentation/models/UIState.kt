package com.example.popcorntime.common.presentation.models

sealed class UIState {
    data object Loading : UIState()
    data object NotConnected : UIState()
    class Success<Data>(val data: Data) : UIState()

    class Failure(val message: String) : UIState()
}
