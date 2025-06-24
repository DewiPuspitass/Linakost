package com.dewipuspitasari0020.linakost.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dewipuspitasari0020.linakost.screen.LoginScreen
import com.dewipuspitasari0020.linakost.screen.MainScreen
import com.dewipuspitasari0020.linakost.screen.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ) {
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
    }
}