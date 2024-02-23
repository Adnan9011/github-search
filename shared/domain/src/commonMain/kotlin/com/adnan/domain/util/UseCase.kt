package com.adnan.domain.util

interface UseCase<Input, Output> {
    suspend operator fun invoke(input: Input): Output
}