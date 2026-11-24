package com.example.retrofit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable
object CartsRoute

@Serializable
data class EditCartRoute(val cartId: String)

@Serializable
object ProfileRoute
