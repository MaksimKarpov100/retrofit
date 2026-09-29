package com.example.retrofit.data.service

import com.example.retrofit.data.model.RecipesResponse
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): RecipesResponse
}