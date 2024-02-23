package com.adnan.data.api

import com.adnan.core.Result
import com.adnan.data.api.model.RepoApiModel
import kotlinx.coroutines.flow.Flow

interface ApiService {
    fun searchRepo(org: String): Flow<Result<List<RepoApiModel>>>
}