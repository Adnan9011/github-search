package com.adnan.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoApiModel(
    val id: Int,
    val name: String,
    @SerialName("forks_count") val forksNumber: Int,
    val owner: Owner
)

@Serializable
data class Owner(
    @SerialName("avatar_url") val avatarUrl: String = ""
)