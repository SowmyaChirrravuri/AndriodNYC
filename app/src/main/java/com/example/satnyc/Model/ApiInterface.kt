package com.example.satnyc.Model

import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolData(): List<School>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatScore(@Query("dbn") id: String): List<SatScores>
}


