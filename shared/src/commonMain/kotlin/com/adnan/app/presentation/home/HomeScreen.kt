package com.adnan.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.adnan.app.presentation.home.widget.EmptyWidget
import com.adnan.app.presentation.home.widget.SearchTopAppBar
import com.adnan.app.presentation.home.widget.ShowRepos
import com.adnan.app.presentation.home.widget.ShowShimmerRepos
import com.adnan.app.presentation.home.widget.ShowSnackbar
import com.adnan.app.presentation.model.RepoUiModel
import com.adnan.app.presentation.util.HOME_TITLE
import com.adnan.app.presentation.util.HighPadding
import com.adnan.app.presentation.util.HomeTextTitleStyle
import com.adnan.app.presentation.util.NormalPadding
import com.adnan.app.presentation.util.ShimmerRepoItemCount
import com.adnan.core.Result
import kotlinx.collections.immutable.PersistentList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            SearchTopAppBar(
                onSearchTextChanged = { org ->
                    viewModel.onSearchTextChanged(org)
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->

        val uiRepos by viewModel.uiRepos.collectAsState()
        var isEnableShimmer by remember { mutableStateOf(false) }

        Column(
            modifier = modifier.padding(paddingValues)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = HOME_TITLE,
                style = HomeTextTitleStyle,
                modifier = Modifier.padding(
                    top = NormalPadding,
                    start = HighPadding,
                    bottom = NormalPadding
                )
            )

            when (uiRepos) {
                Result.Loading -> {
                    isEnableShimmer = true
                    ShowShimmerRepos(
                        isEnableShimmer = true,
                        count = ShimmerRepoItemCount
                    )
                }

                is Result.Failure -> {
                    EmptyWidget()
                    ShowSnackbar(
                        snackbarHostState = snackbarHostState,
                        scope = rememberCoroutineScope(),
                        message = (uiRepos as Result.Failure).error.message
                    )
                }

                is Result.Success -> {
                    isEnableShimmer = false
                    val repos = (uiRepos as Result.Success<PersistentList<RepoUiModel>>).value
                    if (repos.isEmpty()) {
                        EmptyWidget()
                    } else {
                        ShowRepos(
                            repos = repos,
                            onRepoClicked = { repo ->
                                viewModel.updateFakeFavorite(repo)
                            }
                        )
                    }
                }

                else -> {}
            }
        }

    }
}