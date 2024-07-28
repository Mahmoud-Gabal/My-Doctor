package com.example.Doctor.data.remote.Data

data class Article(
    val articles: List<ArticleX>,
    val status: String  ,
    val totalResults: Int
)