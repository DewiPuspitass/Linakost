package com.dewipuspitasari0020.linakost.navigation

sealed class Screen(val route: String) {
    data object Register: Screen("registerscreen")
    data object Home: Screen("mainscreen")
    data object Login: Screen("loginscreen")
    data object TambahPenginap: Screen("penginapscreen")
}