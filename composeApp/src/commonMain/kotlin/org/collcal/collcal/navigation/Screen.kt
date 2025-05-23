package org.collcal.collcal.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
        data object College : Screen("college")
    data object User : Screen("user")
}