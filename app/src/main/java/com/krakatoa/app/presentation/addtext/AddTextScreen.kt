package com.krakatoa.app.presentation.addtext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddTextScreen(
    navController: NavController,
    viewModel: AddTextViewModel = remember { AddTextViewModel() }
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = state.text,
            onValueChange = viewModel::onTextChange,
            label = { Text("Type the text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.sendText() },
            enabled = !state.isSending,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text( if (state.isSending) "Sending..." else "Send" )
        }

        state.successMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = Color.Green)
        }

        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = Color.Red)
        }
    }
}