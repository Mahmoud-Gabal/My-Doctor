package com.example.Doctor.data.remote.Data.NewsRepoImpl

import com.example.Doctor.data.remote.Data.Article
import com.example.Doctor.domain.remote.NewsRepo
import com.example.Doctor.domain.remote.NewsService

class NewsRepoImpl(private val newsService : NewsService) : NewsRepo {
    override suspend fun getNews(): Article {
        return newsService.getNews(
            country = "us",
            category = "health",
            apiKey = "60983feaf40f49599a1086dc3d491358"
        )
    }
}