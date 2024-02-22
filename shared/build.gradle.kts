plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
}

kotlin {
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "feature"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            projects.shared.apply {
                implementation(domain)
                implementation(core)
            }
            compose.apply {
                implementation(runtime)
                implementation(foundation)
                implementation(animation)
                implementation(material3)
            }
            libs.coil.apply {
                implementation(core)
                implementation(compose)
            }
            libs.ktor.apply {
                implementation(core)
            }
            implementation(libs.coroutine)
            libs.koin.apply {
                implementation(core)
                implementation(compose)
            }
            implementation(libs.koin.compose)
            libs.mvvm.apply {
                implementation(compose)
                implementation(compose.flow)
            }
            implementation(libs.kotlinx.collections.immutable)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            compose.apply {
                implementation(preview)
                implementation(uiTooling)
            }
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.adnan.app"
    compileSdk = libs.versions.app.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.app.min.sdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(libs.versions.java.jdk.get().toInt())
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.android.compiler.get()
    }
}
