package com.example.retrofit.data

import com.example.retrofit.data.service.ProductService
import com.example.retrofit.data.service.RecipeService
import com.example.retrofit.data.service.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

object RetrofitClient {

    private val proxy = Proxy(
        Proxy.Type.HTTP,
        InetSocketAddress(
            "10.207.106.59",
            3128    )
    )

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .proxy(proxy)
        .addInterceptor(logging)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val recipeService: RecipeService = retrofit.create(RecipeService::class.java)
    val userService: UserService = retrofit.create(UserService::class.java)
    val productService: ProductService = retrofit.create(ProductService::class.java)
}