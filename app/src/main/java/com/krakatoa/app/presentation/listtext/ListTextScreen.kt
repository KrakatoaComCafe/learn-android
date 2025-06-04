package com.krakatoa.app.presentation.listtext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.krakatoa.app.data.remote.model.TextResponse

@OptIn(ExperimentalMaterial3Api::class)
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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Texts") },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                when (state) {
                    is ListTextUiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                        )
                    }

                    is ListTextUiState.Error -> {
                        val errorMessage = (state as ListTextUiState.Error).message
                        Text("Error: $errorMessage")
                    }

                    is ListTextUiState.Success -> {
                        val texts = (state as ListTextUiState.Success).texts
                        LazyColumn(
                            modifier = Modifier
                                .padding(padding)
                                .padding(24.dp)
                        ) {
                            items(texts) { textResponse: TextResponse ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    elevation = CardDefaults.cardElevation(4.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = textResponse.text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(16.dp),
                                        color = Color(0xFF212121)
                                    )
                                }
                            }
                        }
                    }

                    ListTextUiState.Idle -> {
                        Text("Waiting action...")
                    }
                }

                FloatingActionButton(
                    onClick = { navController.navigate("add_text") },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Text")
                }
            }
        }
    }
}