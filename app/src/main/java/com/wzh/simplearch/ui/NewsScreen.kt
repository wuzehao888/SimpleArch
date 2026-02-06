package com.wzh.simplearch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wzh.simplearch.R
import com.wzh.simplearch.network.Amphibian

/**
 * create by hao
 * 2026/2/6
 */


@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is AmphibianUiState.Success -> AmphibianScreen(
            modifier = modifier.fillMaxSize(),
            amphibians = amphibianUiState.data
        )

        is AmphibianUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize(),
            retryAction = retryAction
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}


@Composable
fun ErrorScreen(modifier: Modifier = Modifier, retryAction: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopbar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall
            )
        },
        modifier = modifier,
    )
}

@Composable
fun AmphibianScreen(
    modifier: Modifier = Modifier,
    amphibians: List<Amphibian>
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(amphibians) {
            AmphCard(amphibian = it)
        }
    }
}

@Composable
fun AmphCard(modifier: Modifier = Modifier, amphibian: Amphibian) {
    Card(modifier = modifier) {
        Column {
            Text(
                "${amphibian.name}(${amphibian.type})",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .crossfade(true)
                    .data(amphibian.imgSrc)
                    .placeholder(R.drawable.loading_img)
                    .error(R.drawable.ic_broken_image)
                    .build(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f),
                contentDescription = stringResource(R.string.app_name)
            )

            Text(
                amphibian.desc,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}