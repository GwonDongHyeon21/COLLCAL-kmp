package org.collcal.collcal.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import org.collcal.collcal.platform.getToken

class AndroidNavigator : Navigator {
    override val currentScreen: State<String> get() = derivedStateOf { _navigationStack.last() }

    private val _navigationStack = mutableStateListOf("")
    val currentScreenSize: State<Int> get() = derivedStateOf { _navigationStack.size }

    init {
        replaceTo(if (getToken().isEmpty()) Screen.SignIn else Screen.College)
    }

    override fun navigateTo(screen: Screen) {
        _navigationStack.add(screen.route)
    }

    override fun goBack() {
        if (_navigationStack.size > 1)
            _navigationStack.removeAt(_navigationStack.size - 1)
    }

    override fun replaceTo(screen: Screen) {
        _navigationStack[_navigationStack.size - 1] = screen.route
    }

    fun resetTo(screen: Screen) {
        _navigationStack.clear()
        _navigationStack.add(screen.route)
    }

    fun resetAndNavigateTo(popUpScreen: Screen, screen: Screen) {
        _navigationStack.clear()
        _navigationStack.add((popUpScreen.route))
        if (popUpScreen != screen)
            _navigationStack.add(screen.route)
    }
}