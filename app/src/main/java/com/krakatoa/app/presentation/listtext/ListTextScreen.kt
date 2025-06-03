package com.krakatoa.app.presentation.listtext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.krakatoa.app.data.remote.model.TextResponse

@Composable
fun ListTextScreen(
    navController: NavController,
    viewModel: ListTextViewModel = hiltViewModel()
) {
    Column {
        val state by viewModel.uiState.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(lifecycleOwner) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadTexts()
            }
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

            FloatingActionButton(
                onClick = {navController.navigate("add_text")},
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Text")
            }
        }
    }
}