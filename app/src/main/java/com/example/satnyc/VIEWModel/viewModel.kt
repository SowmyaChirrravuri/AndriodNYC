package com.example.satnyc.VIEWModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satnyc.Model.Repo
import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School


import kotlinx.coroutines.launch

class schoolViewModel : ViewModel() {

    var schoolState = mutableStateOf<List<School>?>(null)
    val satScoreState= mutableStateOf<List<SatScores>?>(null)

    fun getSchools() {
        viewModelScope.launch {
            try {
                val schoolResponse = Repo().apiInterface.getSchoolData()
                schoolState.value = schoolResponse
            } catch (exception: Exception) {

            }

        }

    }

    fun getSatScores(dbn: String) {
        viewModelScope.launch {
            try {
                val satScores = Repo().apiInterface.getSatScore(dbn)
                satScoreState.value = satScores
                Log.d("SAT SCORE OF SCHOOL", satScoreState.value.toString())
            } catch (exception: Exception) {
                Log.d("Exception occured", "Gowtham")
            }

        }
    }
}

