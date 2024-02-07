package com.example.popcorntime.old_needs_sorting.core.state

sealed class UIState {
    object Loading : UIState()
    object NotConnected : UIState()
    class Success<Data>(val data: Data) : UIState()

    class Failure(val message: String) : UIState()
}
