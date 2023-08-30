package com.example.satnyc.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satnyc.repo.DataRepository
import com.example.satnyc.data.SatScores
import com.example.satnyc.data.School
import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    // Sealed class representing different UI states
    sealed class ViewState<out T> {
        object Loading : ViewState<Nothing>()
        data class Success<T>(val data: T) : ViewState<T>()
        data class Error(val message: String) : ViewState<Nothing>()
    }

    val schoolState: MutableState<ViewState<List<School>>> = mutableStateOf(ViewState.Loading)
    val satScoreState: MutableState<ViewState<List<SatScores>>> = mutableStateOf(ViewState.Loading)


    fun getSchools() {
        viewModelScope.launch {
            try {
                val schoolResponse = dataRepository.getSchoolData()
                schoolState.value = ViewState.Success(schoolResponse)
            } catch (exception: Exception) {
                schoolState.value = ViewState.Error(exception.message ?: "An error occurred")
            }
        }
    }

    fun getSatScores(dbn: String) {
        viewModelScope.launch {
            try {
                val satScores = dataRepository.getSatScore(dbn)
                satScoreState.value = ViewState.Success(satScores)
            } catch (exception: Exception) {
                satScoreState.value = ViewState.Error(exception.message ?: "An error occurred")
            }
        }
    }
}

