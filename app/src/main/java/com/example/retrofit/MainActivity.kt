package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.data.model.Company
import com.example.retrofit.data.model.User
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.ProductsViewModel
import com.example.retrofit.ui.theme.viewmodel.RecipesViewModel
import com.example.retrofit.ui.theme.viewmodel.UsersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                val recipesViewModel: RecipesViewModel = viewModel()
                val productsViewModel: ProductsViewModel = viewModel()

                recipesViewModel.loadRecipes()
                recipesViewModel.deleteRecipe()
                productsViewModel.updateProduct(10)
            }
        }
    }
}