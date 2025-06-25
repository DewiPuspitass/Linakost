package com.dewipuspitasari0020.linakost.navigation

import com.dewipuspitasari0020.linakost.screen.KEY_ID_PENGINAP

sealed class Screen(val route: String) {
    data object Register: Screen("registerscreen")
    data object Home: Screen("mainscreen")
    data object Login: Screen("loginscreen")
    data object TambahPenginap: Screen("penginapscreen")
    data object UbahPenginap: Screen("penginapscreen/{$KEY_ID_PENGINAP}") {
        fun withId(id: Int) = "penginapscreen/$id"
    }
    data object SplashScreen: Screen("splashScreen")

}