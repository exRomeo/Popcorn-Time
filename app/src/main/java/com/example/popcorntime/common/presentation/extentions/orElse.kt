package com.example.popcorntime.common.presentation.extentions

fun <T> T?.orElse(other: T): T = this ?: other

