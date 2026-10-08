package com.example.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val company: Company
)