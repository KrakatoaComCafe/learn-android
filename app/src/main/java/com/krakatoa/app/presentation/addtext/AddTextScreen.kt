package com.krakatoa.app.presentation.addtext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddTextScreen(
    navController: NavController,
    viewModel: AddTextViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Type text here") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.addText(text) }) {
            Text("Add text")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is AddTextUiState.Loading -> CircularProgressIndicator()

            is AddTextUiState.Success -> {
                val textToSent = (state as AddTextUiState.Success).text
                Text("Text sent successfully: [${textToSent}]")
                navController.popBackStack()
            }

            is AddTextUiState.Error -> {
                val message = (state as AddTextUiState.Error).message
                Text("Error: [$message]", color = Color.Red)
            }

            AddTextUiState.Idle -> {}
        }
    }
}