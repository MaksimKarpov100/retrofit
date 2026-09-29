package com.example.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String
)