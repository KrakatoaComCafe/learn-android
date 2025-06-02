package com.krakatoa.app.presentation.listtext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.krakatoa.app.data.remote.model.TextResponse

@Composable
fun ListTextScreen(
    navController: NavController,
    viewModel: ListTextViewModel = hiltViewModel()
) {
    Column {
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadTexts()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when(state) {
                is ListTextUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is ListTextUiState.Error -> {
                    val errorMessage = (state as ListTextUiState.Error).message
                    Text("Error: $errorMessage")
                }

                is ListTextUiState.Success -> {
                    val texts = (state as ListTextUiState.Success).texts
                    LazyColumn {
                        items(texts) { textResponse: TextResponse ->
                            Text(
                                text = textResponse.text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                ListTextUiState.Idle -> {
                    Text("Waiting action...")
                }
            }
        }
    }
}