package com.myapp.ccounter.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ProductsListResponse(
    val products: List<ProductsListItemRemote>
)

@Serializable
data class ProductResponse(
    val results: ProductRemote
)
