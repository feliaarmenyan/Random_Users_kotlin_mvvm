package com.randomusers_kotlin_mvvm.data.remote.api

import com.randomusers_kotlin_mvvm.data.remote.model.RandomUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersApiService {

    @GET("api")
    suspend fun getRandomUsers(
        @Query("page") page: Int,
        @Query("results") results: Int?
    ): RandomUsers
}