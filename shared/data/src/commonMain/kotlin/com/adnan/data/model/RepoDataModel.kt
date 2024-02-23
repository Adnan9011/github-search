package com.adnan.data.model

import com.adnan.data.api.model.RepoApiModel

data class RepoDataModel(val id: Int)

fun RepoApiModel.toData() = RepoDataModel(
    id = id
)