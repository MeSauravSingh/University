package com.example.universities.retrofit

import com.example.universities.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search")
    suspend fun getUniversities(
        @Query("country")  address: String
    ) : Response<List<University>>

}