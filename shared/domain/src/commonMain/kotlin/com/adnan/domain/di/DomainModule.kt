package com.adnan.domain.di

import com.adnan.core.Result
import com.adnan.domain.model.RepoDomainModel
import com.adnan.domain.usecase.GetSearchRepoUseCase
import com.adnan.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetSearchRepoUseCase) { bind<UseCase<String, Flow<Result<List<RepoDomainModel>>>>>() }
}