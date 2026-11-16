package com.example.retrofit.data.service

import com.example.retrofit.data.model.CartsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CartService {

    @GET("carts/records")
    suspend fun getCart(@Header("Authorization") token: String) : CartsResponse
}
