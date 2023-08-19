package com.example.satnyc.VIEWModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satnyc.Model.Repo
import com.example.satnyc.dataclass.satScores
import com.example.satnyc.dataclass.school

import kotlinx.coroutines.launch

class schoolViewModel:ViewModel() {

    val schoolViewModelScores = mutableStateOf<List<school>?>(null)
    val satScoreViewModel= mutableStateOf<List<satScores>?>(null)

    fun getSchoolScores(id:String) {
        viewModelScope.launch {
            val satScoreResponse = Repo().apiInterface.getSchoolData(id)
            schoolViewModelScores.value=satScoreResponse

            val satScoresMath=Repo().apiInterface.getSatScoreMathReading(id)
            satScoreViewModel.value=satScoresMath
            println("satScoreViewModel.value")
        }
    }
}

