package com.krakatoa.app.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.krakatoa.app.presentation.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    Column {
        Text("Main Screen")
        Button(onClick = {navController.navigate(Screen.AddText.route)}) {
            Text("Add text")
        }
        Button(onClick = {navController.navigate(Screen.ListText.route)}) {
            Text("List text")
        }
    }
}