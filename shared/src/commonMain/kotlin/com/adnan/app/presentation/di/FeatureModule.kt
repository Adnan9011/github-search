package com.adnan.app.presentation.di

import com.adnan.app.di.viewModelDefinition
import com.adnan.app.presentation.home.HomeViewModel
import org.koin.dsl.module

val featureModule = module {
    viewModelDefinition { HomeViewModel() }
}