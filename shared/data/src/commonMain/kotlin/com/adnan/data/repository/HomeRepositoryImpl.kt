package com.adnan.data.repository

import com.adnan.core.Error
import com.adnan.core.Result
import com.adnan.core.map
import com.adnan.data.api.ApiService
import com.adnan.data.model.RepoDataModel
import com.adnan.data.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    val api: ApiService
) : HomeRepository {
    override fun searchRepo(org: String): Flow<Result<List<RepoDataModel>>> =
        api.searchRepo(org).map { result ->
            result.map { list ->
                list.map {
                    it.toData()
                }
            }
        }.catch { throwable ->
            Result.Failure(
                Error(
                    message = throwable.message ?: "",
                    throwable = throwable
                )
            )
        }
}