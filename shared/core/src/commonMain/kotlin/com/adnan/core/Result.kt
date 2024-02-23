package com.adnan.core

sealed class Result<out V> {

    object Loading : Result<Nothing>()
    data class Success<out V : Any?>(val value: V) : Result<V>()
    data class Failure(val error: Error) : Result<Nothing>()
}

inline fun <V> Result<V>.fold(
    loading: (Unit) -> Unit,
    success: (V) -> Unit,
    failure: (Error) -> Unit
) =
    when (this) {
        is Result.Loading -> loading(Unit)
        is Result.Success -> success(value)
        is Result.Failure -> failure(error)
    }

inline fun <V, U> Result<V>.map(transform: (V) -> U): Result<U> {

    return when (this) {
        Result.Loading -> Result.Loading
        is Result.Success -> Result.Success(transform(value))
        is Result.Failure -> Result.Failure(error)
    }
}

