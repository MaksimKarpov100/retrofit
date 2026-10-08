package com.example.retrofit.ui.theme.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.model.Dimensions
import com.example.retrofit.data.model.Product
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {

    fun updateProduct(productId: Int) {
        viewModelScope.launch {
            try {
                val beforeProduct = RetrofitClient.productService.getProduct(productId)
                Log.d("ProductResponse", "--- before editing ---")
                Log.d("ProductResponse", "id: ${beforeProduct.id}")
                Log.d("ProductResponse", "title: ${beforeProduct.title}")
                Log.d("ProductResponse", "price: ${beforeProduct.price}")
                Log.d("ProductResponse", "width: ${beforeProduct.dimensions.width}")
                Log.d("ProductResponse", "height: ${beforeProduct.dimensions.height}")
                Log.d("ProductResponse", "depth: ${beforeProduct.dimensions.depth}")
                Log.d("ProductResponse", "weight: ${beforeProduct.weight}")

                val newProduct = beforeProduct.copy(
                    title = "Робот-пылесос CleanBot Max",
                    price = 33600.0,
                    dimensions = Dimensions(
                        width = 350.0,
                        height = 350.0,
                        depth = 95.0
                    ),
                    weight = 3.5
                )

                val afterProduct = RetrofitClient.productService.updateProduct(93, newProduct)
                Log.d("ProductResponse", "--- after editing ---")
                Log.d("ProductResponse", "id: ${afterProduct.id}")
                Log.d("ProductResponse", "title: ${afterProduct.title}")
                Log.d("ProductResponse", "price: ${afterProduct.price}")
                Log.d("ProductResponse", "width: ${afterProduct.dimensions.width}")
                Log.d("ProductResponse", "height: ${afterProduct.dimensions.height}")
                Log.d("ProductResponse", "depth: ${afterProduct.dimensions.depth}")
                Log.d("ProductResponse", "weight: ${afterProduct.weight}")

            } catch (e: Exception) {
                Log.e("ProductResponse", "error: ${e.message}")
            }
        }
    }
}