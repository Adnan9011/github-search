package com.adnan.data.api

import com.adnan.core.Error
import com.adnan.core.Result
import com.adnan.data.api.model.RepoApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServiceImpl(val httpClient: HttpClient) : ApiService {

    override fun searchRepo(org: String): Flow<Result<List<RepoApiModel>>> =
        flow {
            emit(Result.Loading)

            try {
                val url = "orgs/$org/repos?per_page=100"
                val responseBody = httpClient.get(url) {}.body<List<RepoApiModel>>()

                emit(Result.Success(responseBody))

            } catch (exception: Exception) {
                emit(
                    Result.Failure(
                        Error(
                            message = exception.message ?: "",
                            throwable = exception
                        )
                    )
                )
            }
        }
}