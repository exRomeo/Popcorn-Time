package com.example.popcorntime.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.popcorntime.ui.theme.PopcornTimeTheme

@Composable
@Preview
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTopAppBar() {
    var searchQuery by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text(text = "Home")
    },

        actions = {
            if (!isExpanded) IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(Icons.Default.Search, contentDescription = "")
            }
            if (isExpanded) SearchBar(query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {
                    searchQuery = ""
                    isExpanded = !isExpanded
                },
                active = isActive,
                onActiveChange = { },
                placeholder = { Text(text = "Search") },
                trailingIcon = { Icon(Icons.Default.Search, "") }) {
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
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Default.FilterList, "")
            }

        })
}


@Composable
@Preview
fun SearchAppBarPreview() {
    PopcornTimeTheme() {
        Scaffold(topBar = {
            SearchAppBar("", {}, {}, {})
        }) {
            Column(Modifier.padding(it)) {

            }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shadowElevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primary
    ) {

        TextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search Here...",
                    style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) onTextChange("")
                    else onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked(text)
            }),
            colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                ))
    }
}