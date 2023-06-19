package com.example.popcorntime.presentation.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar() {
    var searchQuery by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = "Home")
        },
        actions = {
            if (!isExpanded)
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(Icons.Default.Search, contentDescription = "")
                }
            IconButton(
                onClick = { /* do something */ }
            ) {
                Icon(Icons.Default.FilterList, "")
            }

            if (isExpanded)
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {
                        searchQuery = ""
                        isExpanded = !isExpanded
                    },
                    active = isActive,
                    onActiveChange = { },
                    placeholder = { Text(text = "Search") },
                    trailingIcon = { Icon(Icons.Default.Search, "") }
                ) {
                    val dummyArray = arrayOf(
                        "Lorem",
                        "ipsum",
                        "dolor",
                        "sit",
                        "amet",
                        "consectetur",
                        "adipiscing",
                        "elit",
                        "sed",
                        "do"
                    )
                    LazyColumn() {
                        items(dummyArray) {
                            Text(text = it)
                        }
                    }
                }
        }
    )
}
