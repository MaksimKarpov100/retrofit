package com.example.retrofit.data.model

data class CartsResponse(
    val items: List<Cart>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)
