package com.example.demojsonplaceholderapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojsonplaceholderapi.web.model.newyorktimes.NewyorkTimesData
import com.example.demojsonplaceholderapi.web.repository.NewYorkTimesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewYorkTimesViewModel @Inject constructor(private val newYorkTimesRepository: NewYorkTimesRepository) :
    ViewModel() {
    val getNewYorkTimesData: StateFlow<NewyorkTimesData?> get() = newYorkTimesRepository.getNewYorkTimesData

    suspend fun getNewYorkTimesData(apiKey: String) {
        viewModelScope.launch {
            newYorkTimesRepository.getNewYorkTimesData(apiKey)
        }
    }
}