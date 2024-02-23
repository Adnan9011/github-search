package com.adnan.data.repository

import com.adnan.core.Result
import com.adnan.data.model.RepoDataModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun searchRepo(org: String): Flow<Result<List<RepoDataModel>>>
}