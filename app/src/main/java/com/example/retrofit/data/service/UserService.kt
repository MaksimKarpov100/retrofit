package com.example.retrofit.data.service

import com.example.retrofit.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("users/add")
    suspend fun addUser(@Body user: User): User
}