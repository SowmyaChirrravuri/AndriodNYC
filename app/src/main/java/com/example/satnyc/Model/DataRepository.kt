package com.example.satnyc.Model

import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School

interface DataRepository {
    suspend fun getSchoolData(): List<School>
    suspend fun getSatScore(dbn: String): List<SatScores>
}