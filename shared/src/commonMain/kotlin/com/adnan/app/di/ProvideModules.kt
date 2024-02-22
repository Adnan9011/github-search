package com.adnan.navigation.di

import com.adnan.app.presentation.di.featureModule
import org.koin.dsl.module

object ProvideModules {

    fun getModules() = module {
        includes(
            featureModule
        )
    }
}