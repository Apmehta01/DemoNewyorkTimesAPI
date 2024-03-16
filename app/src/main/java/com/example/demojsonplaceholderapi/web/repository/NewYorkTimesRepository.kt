package com.example.demojsonplaceholderapi.web.repository

import com.example.demojsonplaceholderapi.web.api.NewYorkTimesAPI
import com.example.demojsonplaceholderapi.web.model.newyorktimes.NewyorkTimesData
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NewYorkTimesRepository @Inject constructor(private val newYorkTimesAPI: NewYorkTimesAPI) {

    private val _getNewYorkTimesData = MutableStateFlow<NewyorkTimesData?>(null)

    val getNewYorkTimesData: MutableStateFlow<NewyorkTimesData?> get() = _getNewYorkTimesData

    suspend fun getNewYorkTimesData(apiKey: String) {
        val results = newYorkTimesAPI.getTopArtStories(apiKey)

        if (results.isSuccessful && results.body() != null) {
            _getNewYorkTimesData.emit(results.body())
        }
    }
}