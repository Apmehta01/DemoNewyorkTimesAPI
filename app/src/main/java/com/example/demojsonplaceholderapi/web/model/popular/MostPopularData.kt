package com.example.demojsonplaceholderapi.web.model.popular

data class MostPopularData(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)