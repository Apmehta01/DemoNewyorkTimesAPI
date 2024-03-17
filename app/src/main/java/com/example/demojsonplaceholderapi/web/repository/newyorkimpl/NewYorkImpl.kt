package com.example.demojsonplaceholderapi.web.repository.newyorkimpl

interface NewYorkImpl {
    suspend fun getBooksData(apiKey: String) {}
    suspend fun getMostPopularData(apiKey: String) {}
    suspend fun getNewYorkTimesData(apiKey: String) {}
}