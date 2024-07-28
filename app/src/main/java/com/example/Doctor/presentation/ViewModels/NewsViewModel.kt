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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getNews()
    }

    fun refresh(){
        viewModelScope.launch{
            _isRefreshing.value = true
            if (uiState == newsEvents.Error || uiState == newsEvents.loading){
                reloadNews()
            }else{
                delay(1000)
                getNews()
            }
            getNews()
            _isRefreshing.value = false
        }
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
    fun reloadNews(){
        uiState = newsEvents.loading
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