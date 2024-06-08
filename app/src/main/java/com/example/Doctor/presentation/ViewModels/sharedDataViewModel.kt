package com.example.Doctor.presentation.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.Doctor.data.remote.Data.ArticleX

class sharedDataViewModel : ViewModel() {

    var articlex by mutableStateOf<ArticleX?>(null)
        private set

    fun passArticlex(articleX: ArticleX){
        articlex = articleX
    }
}