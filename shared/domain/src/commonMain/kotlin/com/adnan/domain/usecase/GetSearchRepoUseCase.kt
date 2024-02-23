package com.adnan.domain.usecase

import com.adnan.core.Result
import com.adnan.core.map
import com.adnan.data.repository.HomeRepository
import com.adnan.domain.model.RepoDomainModel
import com.adnan.domain.model.toDomain
import com.adnan.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchRepoUseCase internal constructor(
    private val repository: HomeRepository
) : UseCase<String, Flow<Result<List<RepoDomainModel>>>> {
    override suspend fun invoke(repo: String): Flow<Result<List<RepoDomainModel>>> {
        return repository.searchRepo(repo).map { result ->
            result.map { list ->
                list.map { it.toDomain() }
            }
        }
    }
}
