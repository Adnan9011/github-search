package com.adnan.domain.model

import com.adnan.data.model.RepoDataModel

data class RepoDomainModel(
    val id: Int,
    val name: String,
    val forksNumber: Int,
    val avatarUrl: String
)

fun RepoDataModel.toDomain() = RepoDomainModel(
    id = id,
    name = name,
    forksNumber = forksNumber,
    avatarUrl = avatarUrl
)