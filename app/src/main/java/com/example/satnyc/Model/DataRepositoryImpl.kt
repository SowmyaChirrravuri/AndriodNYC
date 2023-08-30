package com.example.satnyc.Model

import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : DataRepository {

    override suspend fun getSchoolData(): List<School> {
        return apiInterface.getSchoolData()
    }

    override suspend fun getSatScore(dbn: String): List<SatScores> {
        return apiInterface.getSatScore(dbn)
    }
}