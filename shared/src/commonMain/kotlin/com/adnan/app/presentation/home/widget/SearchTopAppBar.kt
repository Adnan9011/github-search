package com.adnan.app.presentation.home.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    onSearchTextChanged: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    TopAppBar(
        title = { Text(text = "Home") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        actions = {
            TextField(
                value = searchText,
                onValueChange = { text ->
                    searchText = text
                    onSearchTextChanged(text)
                },
                label = { Text("Search") },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Black)
            )
        }
    )
}