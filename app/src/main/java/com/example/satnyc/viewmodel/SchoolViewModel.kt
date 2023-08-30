package com.example.satnyc.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satnyc.Model.DataRepository
import com.example.satnyc.Model.Repo
import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School
import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    var schoolState = mutableStateOf<List<School>?>(null)
    val satScoreState= mutableStateOf<List<SatScores>?>(null)

    fun getSchools() {
        viewModelScope.launch {
            try {
                val schoolResponse = dataRepository.getSchoolData()
                schoolState.value = schoolResponse
            } catch (exception: Exception) {

            }

        }

    }

    fun getSatScores(dbn: String) {
        viewModelScope.launch {
            try {
                val satScores = dataRepository.getSatScore(dbn)
                satScoreState.value = satScores
                Log.d("SAT SCORE OF SCHOOL", satScoreState.value.toString())
            } catch (exception: Exception) {
                Log.d("Exception occured", "Gowtham")
            }

        }
    }
}

