package com.example.demojsonplaceholderapi.web.repository

import com.example.demojsonplaceholderapi.web.api.NewYorkTimesAPI
import com.example.demojsonplaceholderapi.web.model.books.BooksData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BooksDataRepository @Inject constructor(private val api: NewYorkTimesAPI) {
    private val _getBooksData = MutableStateFlow<BooksData?>(null)
    val getsBooksData: StateFlow<BooksData?> get() = _getBooksData
    suspend fun getBooksData(apiKey: String) {
        val results = api.getBooksData(apiKey)

        if (results.isSuccessful && results.body() != null) {
            _getBooksData.emit(results.body()!!)
        }
    }
}