package com.example.popcorntime.common.presentation.paginating.domain

interface Paginator<Key, Item> {
    suspend fun loadNextPage()

    fun reset()
}