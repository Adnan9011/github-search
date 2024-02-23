package com.adnan.app

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.adnan.app.di.ProvideModules
import com.adnan.app.navigation.Destinations
import com.adnan.app.presentation.home.HomeScreen
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    KoinApplication(
        application = {
            modules(ProvideModules.getModules())
        }
    ) {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        PreComposeApp {
            val navigator = rememberNavigator()

            NavHost(
                navigator = navigator,
                initialRoute = Destinations.HOME_ROUT,
            ) {
                scene(
                    route = Destinations.HOME_ROUT
                ) {
                    HomeScreen(
                        viewModel = koinInject()
                    )
                }
            }
        }
    }
}