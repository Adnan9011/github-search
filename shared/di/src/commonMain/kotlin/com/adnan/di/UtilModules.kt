package com.adnan.di

import com.adnan.data.di.dataModule
import com.adnan.domain.di.domainModule
import org.koin.dsl.module

object UtilModules {

    fun getModules() = module {
        includes(
            dataModule,
            domainModule
        )
    }
}