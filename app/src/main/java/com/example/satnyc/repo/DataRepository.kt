package com.example.satnyc.repo

import com.example.satnyc.data.SatScores
import com.example.satnyc.data.School

interface DataRepository {
    suspend fun getSchoolData(): List<School>
    suspend fun getSatScore(dbn: String): List<SatScores>
}