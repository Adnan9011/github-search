package com.adnan.app.di

import com.adnan.app.presentation.di.featureModule
import com.adnan.di.UtilModules
import org.koin.dsl.module

object ProvideModules {

    fun getModules() = module {
        includes(
            featureModule,
            UtilModules.getModules()
        )
    }
}