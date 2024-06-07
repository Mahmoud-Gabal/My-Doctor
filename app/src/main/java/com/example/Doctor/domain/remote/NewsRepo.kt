package com.example.Doctor.domain.remote

import com.example.Doctor.data.remote.Data.Article
import retrofit2.http.Query

interface NewsRepo {
    suspend fun getNews() : Article
}
