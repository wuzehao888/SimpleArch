package com.wzh.simplearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wzh.simplearch.ui.AmphibianViewModel
import com.wzh.simplearch.ui.NewsScreen
import com.wzh.simplearch.ui.NewsTopbar
import com.wzh.simplearch.ui.theme.SimpleArchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleArchTheme {
                NewsApp()
            }
        }
    }
}

@Composable
fun NewsApp(modifier: Modifier = Modifier) {
    val amphibianViewModel: AmphibianViewModel =
        viewModel(factory = AmphibianViewModel.factory)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NewsTopbar() }) { innerPadding ->
        NewsScreen(
            modifier = Modifier.padding(innerPadding),
            amphibianUiState = amphibianViewModel.amphibianUiState,
            retryAction = {
                amphibianViewModel.getAmphibians()
            }
        )
    }
}