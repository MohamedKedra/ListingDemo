@file:OptIn(ExperimentalMaterial3Api::class)

package com.mohamed.kedra.listingdemo.presentation.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mohamed.kedra.listingdemo.R
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.presentation.utils.ErrorScreen
import com.mohamed.kedra.listingdemo.presentation.utils.LaunchesAppBar
import com.mohamed.kedra.listingdemo.presentation.utils.LoadingScreen

@Composable
fun DetailsScreen(
    id : String,
    onBackClicked: () -> Unit
){
    DetailsScreenContent(
        id = id,
        onBackClicked = onBackClicked
    )
}

@Composable
fun DetailsScreenContent(
    viewModel: DetailsViewModel = hiltViewModel(),
    id : String,
    onBackClicked: () -> Unit
){
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(DetailsEvent.GetAllDetails(id))
    }

    Scaffold(
        topBar = {
            LaunchesAppBar(
                title = context.getString(R.string.details_screen_title,id),
                hasBack = true,
                onBackClicked = onBackClicked
            )
        },
    ) { paddingValues ->
        when{
            state.isLoading -> {
                LoadingScreen(paddingValues)
            }

            state.error != null -> {
                ErrorScreen(paddingValues,state.error)
            }

            state.launchEntity != null -> {
                DetailsScreenContent(state.launchEntity)
            }
        }
    }
}

@Composable
fun DetailsScreenContent(launchEntity: LaunchEntity){
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        val context = LocalContext.current

        launchEntity.apply {


            Spacer(Modifier.height(52.dp))
            AsyncImage(
                model = missionPatch,
                contentDescription = "Launch patch",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier.fillMaxWidth()
                    .height(450.dp)
                    .padding(vertical = 16.dp),
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                context.getString(R.string.rocket),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                context.getString(R.string.name).plus(launchEntity.rocketName),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                context.getString(R.string.type).plus(launchEntity.rocketType),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                context.getString(R.string.id).plus(launchEntity.rocketId),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                context.getString(R.string.mission),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                context.getString(R.string.name).plus(launchEntity.missionName),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                context.getString(R.string.site),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                launchEntity.site,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}