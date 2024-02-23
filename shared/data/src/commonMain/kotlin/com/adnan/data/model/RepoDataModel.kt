package com.adnan.data.model

import com.adnan.data.api.model.RepoApiModel

data class RepoDataModel(
    val id: Int,
    val name: String,
    val forksNumber: Int,
    val avatarUrl: String
)

fun RepoApiModel.toData() = RepoDataModel(
    id = id,
    name = name,
    forksNumber = forksNumber,
    avatarUrl = owner.avatarUrl
)