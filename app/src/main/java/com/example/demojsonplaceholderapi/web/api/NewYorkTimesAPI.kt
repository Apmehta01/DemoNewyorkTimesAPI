package com.example.demojsonplaceholderapi.web.api

import com.example.demojsonplaceholderapi.web.model.books.BooksData
import com.example.demojsonplaceholderapi.web.model.newyorktimes.NewyorkTimesData
import com.example.demojsonplaceholderapi.web.model.popular.MostPopularData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesAPI {

    @GET("/svc/topstories/v2/arts.json")
    suspend fun getTopArtStories(
        @Query("api-key") apiKey: String
    ): Response<NewyorkTimesData>

    @GET("/svc/books/v3/lists/current/hardcover-fiction.json")
    suspend fun getBooksData(
        @Query("api-key") apiKey: String
    ): Response<BooksData>

    @GET("/svc/mostpopular/v2/viewed/1.json")
    suspend fun getMostPopularData(
        @Query("api-key") apiKey: String
    ): Response<MostPopularData>
}