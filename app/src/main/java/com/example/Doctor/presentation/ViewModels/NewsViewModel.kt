package com.example.Doctor.presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Doctor.data.remote.Data.Article
import com.example.Doctor.domain.remote.UseCases.GetNewsCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface newsEvents{
    object loading : newsEvents
    data class success(val article : Article ) : newsEvents
    object Error  : newsEvents
}
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val neweCase : GetNewsCase
) : ViewModel() {

    var uiState : newsEvents by mutableStateOf(newsEvents.loading)
    private set

    init {
        getNews()
    }

    fun getNews(){
        viewModelScope.launch {
            try {
                val news = neweCase()
                uiState = newsEvents.success(news)
            }catch (e : Exception){
                Log.d("NewsError",e.message.toString())
                uiState = newsEvents.Error
            }
        }
    }

}