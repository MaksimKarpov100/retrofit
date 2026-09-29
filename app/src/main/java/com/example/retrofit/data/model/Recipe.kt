package com.example.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("name") val name: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("rating") val rating: Double
)