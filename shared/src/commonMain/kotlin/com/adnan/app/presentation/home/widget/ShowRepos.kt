package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.adnan.app.presentation.model.RepoUiModel
import kotlinx.collections.immutable.PersistentList

@Composable
fun ShowRepos(
    repos: PersistentList<RepoUiModel>,
    onRepoClicked: (RepoUiModel) -> Unit
) {
    LazyColumn {
        items(
            items = repos,
            key = { repo -> repo.id }
        ) { repo ->
            RepoItem(
                repo = repo,
                onRepoClicked = onRepoClicked
            )
        }
    }
}