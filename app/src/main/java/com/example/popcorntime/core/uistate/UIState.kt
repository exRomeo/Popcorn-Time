package com.example.popcorntime.core.uistate

import com.example.popcorntime.data.models.Movie

sealed class UIState {
    object Loading : UIState()
    object NotConnected : UIState()
    class Success(val data: Movie?) : UIState()
    class Failure(val message: String) : UIState()
}
