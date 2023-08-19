package com.example.satnyc.Model

import com.example.satnyc.dataclass.satScores
import com.example.satnyc.dataclass.school
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolData(@Query("dbn") id: String): List<school>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatScoreMathReading(@Query("dbn") id: String): List<satScores>
}


