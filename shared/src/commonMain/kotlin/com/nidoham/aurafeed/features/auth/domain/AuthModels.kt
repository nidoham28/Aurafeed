package com.nidoham.aurafeed.features.auth.domain

enum class Gender(val label: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other")
}

enum class AuthMode(val label: String) {
    LOGIN("Login"),
    REGISTER("Register")
}
