package com.example.demojsonplaceholderapi.web.repository

import com.example.demojsonplaceholderapi.web.api.NewYorkTimesAPI
import com.example.demojsonplaceholderapi.web.model.popular.MostPopularData
import com.example.demojsonplaceholderapi.web.repository.newyorkimpl.NewYorkImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MostPopularRepository @Inject constructor(private val api: NewYorkTimesAPI) : NewYorkImpl {

    private val _getMostPopularData = MutableStateFlow<MostPopularData?>(null)

    val getMostPopularData: StateFlow<MostPopularData?> get() = _getMostPopularData


    override suspend fun getMostPopularData(apiKey: String) {
        val result = api.getMostPopularData(apiKey)

        if (result.isSuccessful && result.body() != null) {
            _getMostPopularData.emit(result.body())
        }
    }
}