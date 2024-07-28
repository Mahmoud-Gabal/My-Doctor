package com.example.Doctor.domain.remote


import com.example.Doctor.data.remote.Data.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query(value = "country") country : String,
        @Query(value = "category") category : String,
        @Query(value = "apiKey") apiKey : String,
    ) : Article
}