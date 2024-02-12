package com.example.popcorntime.modules.movie_details.presentation.contract

import com.example.popcorntime.common.presentation.contract.BaseUIEvent

sealed class MovieDetailsUIEvent : BaseUIEvent {
    data object NavigateBack : MovieDetailsUIEvent()
}