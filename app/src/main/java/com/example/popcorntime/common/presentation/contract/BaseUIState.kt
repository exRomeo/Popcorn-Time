package com.example.popcorntime.common.presentation.contract

import androidx.compose.runtime.MutableState

interface BaseUIState{
    val networkError: MutableState<Boolean>
    val loading: MutableState<Boolean>
}