package com.nidoham.aurafeed.features.auth.state

fun validateUsername(username: String): String? {
    if (username.isBlank()) {
        return "Username is required"
    }

    if (username.length !in 3..20) {
        return "Username must be 3-20 characters"
    }

    val usernamePattern = Regex("^[a-zA-Z0-9_.]+$")
    if (!username.matches(usernamePattern)) {
        return "Only letters, numbers, _ and . allowed"
    }

    return null
}

fun validateEmail(email: String): String? {
    if (email.isBlank()) {
        return "Email is required"
    }

    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    if (!email.matches(emailPattern)) {
        return "Enter a valid email"
    }

    return null
}

fun validatePassword(password: String): String? {
    if (password.isBlank()) {
        return "Password is required"
    }

    if (password.length < 8) {
        return "Password must be at least 8 characters"
    }

    return null
}

fun validateDateOfBirth(dateOfBirth: String): String? {
    if (dateOfBirth.isBlank()) {
        return "Date of birth is required"
    }

    val dobPattern = Regex("^\\d{4}-\\d{2}-\\d{2}$")
    if (!dobPattern.matches(dateOfBirth)) {
        return "Use YYYY-MM-DD format"
    }

    return null
}