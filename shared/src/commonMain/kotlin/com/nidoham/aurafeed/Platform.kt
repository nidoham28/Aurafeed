package com.nidoham.aurafeed

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform