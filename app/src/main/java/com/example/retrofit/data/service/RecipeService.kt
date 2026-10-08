package com.example.retrofit.data.service

import com.example.retrofit.data.model.Recipe
import com.example.retrofit.data.model.RecipesResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): RecipesResponse

    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: Int): Recipe
}