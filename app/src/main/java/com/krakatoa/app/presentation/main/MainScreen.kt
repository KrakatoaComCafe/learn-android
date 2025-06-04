package com.krakatoa.app.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krakatoa.app.presentation.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    Surface(color = Color(0xFFF5F5F5), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF212121)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate(Screen.AddText.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Add Text") }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { navController.navigate(Screen.ListText.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("List Text") }
        }
    }
}