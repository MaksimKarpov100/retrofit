package com.example.retrofit.data.service

import com.example.retrofit.data.model.Cart
import com.example.retrofit.data.model.CartsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface CartService {
    @GET("collections/carts/records")
    suspend fun getCarts(@Header("Authorization") token: String): CartsResponse

    @GET("collections/carts/records/{id}")
    suspend fun getCartById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Cart

    @PATCH("collections/carts/records/{id}")
    suspend fun updateCart(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body cart: Cart
    ): Cart
}