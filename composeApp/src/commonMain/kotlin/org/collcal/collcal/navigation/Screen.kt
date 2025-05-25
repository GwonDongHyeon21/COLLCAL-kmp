package org.collcal.collcal.navigation

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("on_boarding")
    data object SignIn : Screen("sign_in")
    data object SignUp : Screen("sign_up")
    data object College : Screen("college")
    data object User : Screen("user")
}