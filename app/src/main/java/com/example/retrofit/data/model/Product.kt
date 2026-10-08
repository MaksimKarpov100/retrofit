package com.example.retrofit.data.model

data class Product(
    val id: Int? = null,
    val title: String,
    val price: Double,
    val dimensions: Dimensions,
    val weight: Double
)
