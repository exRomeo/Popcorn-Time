package com.example.popcorntime.presentation.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.popcorntime.R
import com.example.popcorntime.core.state.SearchWidgetState
import com.example.popcorntime.core.state.SortBy


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(onSearchTriggered: () -> Unit, onFilterClicked: (SortBy) -> Unit) {

    var isMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var menuItemSelected: SortBy by rememberSaveable {
        mutableStateOf(SortBy.Popular)
    }
    val changeSelection: (SortBy) -> Unit = {
        if (menuItemSelected != it) {
            menuItemSelected = it
            onFilterClicked(it)
        }
        isMenuVisible = false
    }
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.home))
    },
        actions = {
            IconButton(onClick = onSearchTriggered) {
                Icon(Icons.Default.Search, contentDescription = "")
            }
            ExposedDropdownMenuBox(
                expanded = isMenuVisible,
                onExpandedChange = { isMenuVisible = !isMenuVisible }
            ) {
                IconButton(modifier = Modifier.menuAnchor(), onClick = { isMenuVisible = true }) {
                    Icon(Icons.Default.FilterList, "")
                }

                DropdownMenu(
                    expanded = isMenuVisible,
                    onDismissRequest = {
                        isMenuVisible = false
                    }) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.sort_by)) },
                        onClick = {}
                    )

                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(stringResource(id = R.string.popular))
                                RadioButton(
                                    selected = menuItemSelected == SortBy.Popular,
                                    onClick = { changeSelection(SortBy.Popular) })
                            }
                        },
                        onClick = { changeSelection(SortBy.Popular) })

                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(stringResource(id = R.string.top_rated))
                                RadioButton(
                                    selected = menuItemSelected == SortBy.TopRated,
                                    onClick = { changeSelection(SortBy.TopRated) }
                                )
                            }
                        },
                        onClick = { changeSelection(SortBy.TopRated) }
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    onFilterClicked: (SortBy) -> Unit
) {

    AnimatedContent(
        targetState = searchWidgetState,
        transitionSpec = {
            when (targetState) {
                SearchWidgetState.Opened -> {
                    (expandHorizontally { width -> width } + fadeIn()).togetherWith(
                        shrinkHorizontally { width -> width } + fadeOut())
                }

                SearchWidgetState.Closed -> {
                    (expandHorizontally { width -> width } + fadeIn()).togetherWith(
                        shrinkHorizontally { width -> width } + fadeOut())
                }
            }
        },
        label = "AnimatedAppBarContent"
    ) {
        when (it) {
            SearchWidgetState.Closed -> {
                DefaultAppBar(
                    onSearchTriggered = onSearchTriggered,
                    onFilterClicked = onFilterClicked
                )
            }

            SearchWidgetState.Opened -> {
                SearchAppBar(
                    text = searchTextState,
                    onTextChange = onTextChange,
                    onCloseClicked = onCloseClicked,
                    onSearchClicked = onSearchClicked
                )
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
            .height(64.dp),
        shadowElevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.background
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.search_here),
                    style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = { onSearchClicked(text) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty())
                        onTextChange("")
                    else
                        onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close),
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
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onBackground.copy(ContentAlpha.medium),
            )
        )
    }
}
