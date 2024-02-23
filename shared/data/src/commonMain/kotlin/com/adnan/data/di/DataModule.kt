package com.adnan.data.di

import com.adnan.data.api.ApiService
import com.adnan.data.api.ApiServiceImpl
import com.adnan.data.repository.HomeRepository
import com.adnan.data.repository.HomeRepositoryImpl
import com.adnan.data.util.KtorLogger
import com.adnan.data.util.Rout
import com.adnan.data.util.TIME_OUT
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val dataModule = module {
    single<HomeRepository> { HomeRepositoryImpl(api = get()) }

    single {
        HttpClient() {
            install(Logging) {
                logger = KtorLogger()
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(Rout.BASE_URL)
                header("X-GitHub-Api-Version", "2022-11-28")
                header(HttpHeaders.Accept, "application/vnd.github+json")
            }
            install(ContentNegotiation) {
                json(Json {
                    encodeDefaults = false
                    explicitNulls = false
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
            }
        }.also { Napier.base(DebugAntilog()) }
    }

    single { Dispatchers.IO }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }
}