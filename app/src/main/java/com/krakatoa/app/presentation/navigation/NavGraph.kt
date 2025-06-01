package com.krakatoa.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.krakatoa.app.presentation.addtext.AddTextScreen
import com.krakatoa.app.presentation.listtext.ListTextScreen
import com.krakatoa.app.presentation.main.MainScreen

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object AddText : Screen("add_text")
    object ListText: Screen("list_text")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(navController)
        }
        composable(Screen.AddText.route) {
            AddTextScreen(navController)
        }
        composable(Screen.ListText.route) {
            ListTextScreen(navController)
        }
    }
}