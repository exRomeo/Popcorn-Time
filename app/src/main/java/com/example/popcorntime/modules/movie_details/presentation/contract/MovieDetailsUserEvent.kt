package com.example.popcorntime.modules.movie_details.presentation.contract

import com.example.popcorntime.common.presentation.contract.BaseUserEvent

sealed class MovieDetailsUserEvent() : BaseUserEvent{
    data object RefreshScreen : MovieDetailsUserEvent()
    data object BackClicked : MovieDetailsUserEvent()
}
