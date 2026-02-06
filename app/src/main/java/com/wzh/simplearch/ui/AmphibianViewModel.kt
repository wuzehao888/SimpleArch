package com.wzh.simplearch.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.wzh.simplearch.AmphibianApplication
import com.wzh.simplearch.data.AmphibianRepository
import com.wzh.simplearch.network.Amphibian
import kotlinx.coroutines.launch

/**
 * create by hao
 * 2026/2/6
 */

sealed interface AmphibianUiState {
    data class Success(val data: List<Amphibian>) : AmphibianUiState
    object Loading : AmphibianUiState
    object Error : AmphibianUiState
}

class AmphibianViewModel(private val amphibianRepository: AmphibianRepository) :
    ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibianUiState = try {
                val list = amphibianRepository.getAmphibians()
                AmphibianUiState.Success(list)
            } catch (e: Exception) {
                e.printStackTrace()
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as AmphibianApplication
                val amphibianRepository = app.appContainer.amphibianRepository
                AmphibianViewModel(amphibianRepository)
            }
        }
    }
}