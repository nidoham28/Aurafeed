package ui.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// থিমের ৩টি অবস্থা
enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

class ThemeManager {
    // ডিফল্ট হিসেবে সিস্টেম থিম রাখা হলো
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)

    // বাইরের থেকে শুধু পড়ার জন্য (Read-only)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    // থিম পরিবর্তন করার ফাংশন
    fun setTheme(mode: ThemeMode) {
        _themeMode.value = mode
    }

    // টগল করার জন্য (যদি শুধু লাইট/ডার্ক সুইচ করতে চাও)
    fun toggleTheme() {
        _themeMode.value = if (_themeMode.value == ThemeMode.DARK) {
            ThemeMode.LIGHT
        } else {
            ThemeMode.DARK
        }
    }
}

// পুরো অ্যাপে একটাই ইনস্ট্যান্স ব্যবহার করার জন্য (Singleton)
val AppThemeManager = ThemeManager()