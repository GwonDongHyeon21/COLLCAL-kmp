package org.collcal.collcal.navigation

import androidx.compose.runtime.State

interface Navigator {
    val currentScreen: State<String>
    fun navigateTo(screen: Screen)
    fun goBack()
    fun replaceTo(screen: Screen)
}