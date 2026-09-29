package com.example.retrofit.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    fun loadRecipes() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.recipeService.getRecipes()
                for (recipe in response.recipes) {
                    Log.d("RecipesResponse", "name: ${recipe.name}")
                    Log.d("RecipesResponse", "difficulty: ${recipe.difficulty}")
                    Log.d("RecipesResponse", "rating: ${recipe.rating}")
                }
            } catch (e: Exception) {
                Log.e("RecipesResponse", "Ошибка: ${e.message}")
            }
        }
    }
}