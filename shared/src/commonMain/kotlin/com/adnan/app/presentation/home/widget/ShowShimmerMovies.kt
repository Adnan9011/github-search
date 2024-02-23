package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun ShowShimmerRepos(
    isEnableShimmer: Boolean,
    count: Int
) {
    LazyColumn()
    {
        items(count = count) {
            ShimmerRepoItem(isEnableShimmer = isEnableShimmer)
        }
    }
}