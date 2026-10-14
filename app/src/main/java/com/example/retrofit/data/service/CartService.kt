package com.example.retrofit.data.service

import com.example.retrofit.data.model.CartsResponse
import retrofit2.http.GET

interface CartService {

    @GET("/carts/records")
    suspend fun getCart() : CartsResponse
}
