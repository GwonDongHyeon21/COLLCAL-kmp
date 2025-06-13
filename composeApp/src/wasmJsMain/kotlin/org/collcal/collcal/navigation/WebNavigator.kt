package org.collcal.collcal.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.window

class WebNavigator : Navigator {
    override val currentScreen: State<String> get() = _currentScreen

    private val _currentScreen = mutableStateOf("")

    init {
        if (window.location.pathname.removePrefix("/").isBlank()) {
            _currentScreen.value = Screen.SignIn.route
            window.history.replaceState(null, "", "/${Screen.SignIn.route}")
        }
    }

    override fun navigateTo(screen: Screen) {
        _currentScreen.value = screen.route
        window.history.pushState(null, "", "/${screen.route}")
    }

    override fun goBack() {
        window.history.back()
    }

    override fun replaceTo(screen: Screen) {
        _currentScreen.value = screen.route
        window.history.replaceState(null, "", "/${screen.route}")
    }

    fun onPopState() {
        _currentScreen.value =
            window.location.pathname.removePrefix("/").ifBlank { Screen.SignIn.route }
    }
}