package com.example.demojsonplaceholderapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojsonplaceholderapi.web.model.popular.MostPopularData
import com.example.demojsonplaceholderapi.web.repository.MostPopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(private val repository: MostPopularRepository) :
    ViewModel() {

    val getMostPopularData: StateFlow<MostPopularData?> get() = repository.getMostPopularData

    suspend fun getMostPopularData(apiKey: String) {
        viewModelScope.launch {
            repository.getMostPopularData(apiKey)
        }
    }
}