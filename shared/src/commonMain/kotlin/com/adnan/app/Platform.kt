package com.adnan.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform