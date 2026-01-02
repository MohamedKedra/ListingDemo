package com.mohamed.kedra.listingdemo.presentation.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.mohamed.kedra.listingdemo.R
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.presentation.utils.BottomLoader
import com.mohamed.kedra.listingdemo.presentation.utils.EmptyState
import com.mohamed.kedra.listingdemo.presentation.utils.ErrorSnackbar
import com.mohamed.kedra.listingdemo.presentation.utils.FullScreenLoader
import com.mohamed.kedra.listingdemo.presentation.utils.LaunchesAppBar


@Composable
fun HomeScreen(
    onLaunchClick: (String) -> Unit
) {
    HomeScreenContent(
        onLaunchClick = onLaunchClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onLaunchClick: (String) -> Unit
) {

    val state = viewModel.state.collectAsState().value
    val items = viewModel.launches.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(items.loadState) {
        viewModel.handlePagingLoadState(items.loadState)
    }
    Scaffold(
        topBar = {
            LaunchesAppBar(
                title = context.getString(R.string.home_screen_title),
                hasBack = false,
                onBackClicked = {

                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(items.itemCount) { index ->
                    val launch = items[index]
                    launch?.let {
                        if (index == 0) Spacer(modifier = Modifier.height(10.dp))
                        LaunchDetailsItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    onLaunchClick.invoke(it.id)
                                }, launchEntity = it
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                if (items.loadState.append is LoadState.Loading) {
                    item { BottomLoader() }
                }
            }

            if (items.itemCount == 0 && state.showEmptyState) EmptyState()
            state.errorMessage?.let { ErrorSnackbar(it) { items.retry() } }
            if (items.loadState.refresh is LoadState.Loading) FullScreenLoader()
        }
    }
}

@Composable
fun LaunchDetailsItem(
    modifier: Modifier, launchEntity: LaunchEntity
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = launchEntity.missionPatch,
                contentDescription = "Launch patch",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )

            Column(
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    launchEntity.rocketName.plus("(id ${launchEntity.id})"),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    launchEntity.missionName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}