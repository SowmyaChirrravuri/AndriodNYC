package com.example.satnyc.repo

import com.example.satnyc.data.SatScores
import com.example.satnyc.data.School
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