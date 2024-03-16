package com.example.demojsonplaceholderapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojsonplaceholderapi.web.model.books.BooksData
import com.example.demojsonplaceholderapi.web.repository.BooksDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val booksDataRepository: BooksDataRepository) :
    ViewModel() {

    val getsBooksData: StateFlow<BooksData?> get() = booksDataRepository.getsBooksData

    suspend fun getsBooksData(apiKey: String) {
        viewModelScope.launch {
            booksDataRepository.getBooksData(apiKey)
        }
    }
}