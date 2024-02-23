package com.adnan.domain.model

import com.adnan.data.model.RepoDataModel

data class RepoDomainModel(val id: Int)

fun RepoDataModel.toDomain() = RepoDomainModel(
    id = id
)