package com.example.retrofit.data.service

import com.example.retrofit.data.model.AuthResponse
import com.example.retrofit.data.model.LoginRequest
import com.example.retrofit.data.model.UserRecord
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("users/auth-with-password")
    suspend fun login(@Body request: LoginRequest) : AuthResponse

    @POST("users/records")
    suspend fun createNewUser(@Body userRecord: UserRecord) : UserRecord
}