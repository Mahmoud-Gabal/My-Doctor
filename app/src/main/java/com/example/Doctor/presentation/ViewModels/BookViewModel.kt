package com.example.Doctor.presentation.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Doctor.domain.local.db.DoctorDao
import com.example.Doctor.domain.local.db.bookmarkedDRs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface BookingEvents{
    data class bookDR(val bookmarkedDRs: bookmarkedDRs) : BookingEvents
    data class cancelBookingDR(val bookmarkedDRs: bookmarkedDRs) : BookingEvents
}
@HiltViewModel
class BookViewModel @Inject constructor(
    private val dao: DoctorDao
) : ViewModel() {

     val bookedList = dao.getAllBookemarkDrs()
//        .stateIn(
//        viewModelScope, SharingStarted.WhileSubscribed(5000), null
//    )

    private val _searchingText = MutableStateFlow("")
    val searchingText = _searchingText.asStateFlow()

    val filtered_list = searchingText.combine(bookedList){searchingText,bookList ->
        bookList?.filter { it.name.contains(searchingText,ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),null)

    fun onBookingEvent(event: BookingEvents){
        when(event){
            is BookingEvents.bookDR -> {
                viewModelScope.launch {
                    dao.addBokkmarkDR(event.bookmarkedDRs)
                }
            }
            is BookingEvents.cancelBookingDR -> {
                viewModelScope.launch {
                    dao.deleteBookmarkDr(event.bookmarkedDRs)
                }
            }
        }
    }

    fun changeSearchTextTo(text : String){
        _searchingText.value = text
    }


}