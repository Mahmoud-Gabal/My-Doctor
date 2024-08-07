package com.example.Doctor.data.remote.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleX(
    val author: String? ,
    val description: String? ,
    val content: String? ,
    val publishedAt: String? ,
    val source: SourceName?,
    val title: String? ,
    val url: String? ,
    val urlToImage: String?
) : Parcelable