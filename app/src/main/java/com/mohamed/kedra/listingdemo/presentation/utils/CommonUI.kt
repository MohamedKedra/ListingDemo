@file:OptIn(ExperimentalMaterial3Api::class)

package com.mohamed.kedra.listingdemo.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(paddingValues : PaddingValues){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(paddingValues: PaddingValues,message : String){

    Text(
        text = message,
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
    )
}

@Composable
fun LaunchesAppBar(title : String,hasBack : Boolean,onBackClicked: () -> Unit){
    TopAppBar(
        title = {Text(title, color = MaterialTheme.colorScheme.onSurfaceVariant)},
        navigationIcon = {
            if (hasBack){
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.clickable{
                        onBackClicked()
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Composable
fun BottomLoader() = Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
}

@Composable
fun FullScreenLoader() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
}

@Composable
fun EmptyState() = Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("No launches found")
}

@Composable
fun ErrorSnackbar(message: String, onDismiss: () -> Unit) = Snackbar(
    action = {
        TextButton(onClick = onDismiss) { Text("Retry") }
    },
    modifier = Modifier.padding(16.dp)
) { Text(message) }




