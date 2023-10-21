package com.myapp.ccounter.data.local

import com.myapp.ccounter.domain.model.Product

interface LocalDataSource {
    suspend fun insertProduct(product: Product)
    suspend fun getProductById(id: Long): Product?
    suspend fun getAllProducts(): List<Product>?
    suspend fun deleteProductById(id: Long)
}