package com.example.satnyc.VIEWModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satnyc.Model.Repo
import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School


import kotlinx.coroutines.launch

class schoolViewModel:ViewModel() {

    var schoolState = mutableStateOf<List<School>?>(null)
    val satScoreState= mutableStateOf<List<SatScores>?>(null)

    fun getSchools() {
        viewModelScope.launch {
            val schoolResponse = Repo().apiInterface.getSchoolData()
            schoolState.value=schoolResponse
        }

    }

    fun getSatScores(dbn:String){
        viewModelScope.launch {
            val satScores=Repo().apiInterface.getSatScore(dbn)
            satScoreState.value=satScores
            println("satScoreViewModel.value")
        }
    }
}

