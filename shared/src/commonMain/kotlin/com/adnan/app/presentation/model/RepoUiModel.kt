package com.adnan.app.presentation.model

import com.adnan.domain.model.RepoDomainModel

data class RepoUiModel(
    val id: Int,
    val name: String,
    val forksNumber: Int,
    val avatarUrl: String,
    val isLiked: Boolean
)

fun RepoDomainModel.toUi(isLiked: Boolean) = RepoUiModel(
    id = id,
    name = name,
    forksNumber = forksNumber,
    avatarUrl = avatarUrl,
    isLiked = isLiked
)