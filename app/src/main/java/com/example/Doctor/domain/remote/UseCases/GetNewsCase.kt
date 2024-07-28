package com.example.Doctor.domain.remote.UseCases

import com.example.Doctor.data.remote.Data.Article
import com.example.Doctor.domain.remote.NewsRepo

class GetNewsCase(
    private val newsRepo : NewsRepo
) {
    suspend operator fun invoke() : Article {
       return newsRepo.getNews()
    }
}