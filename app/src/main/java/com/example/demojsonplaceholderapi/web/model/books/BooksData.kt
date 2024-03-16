package com.example.demojsonplaceholderapi.web.model.books

data class BooksData(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: Results,
    val status: String
)