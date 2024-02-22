package com.adnan.app.presentation.home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        topBar = {

        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->

    }
}