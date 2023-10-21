package com.myapp.ccounter.data.remote

import com.myapp.ccounter.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: ProductService,
    private val dispatcher: Dispatcher,
) {

    suspend fun getProductsList(productsName: String) = withContext(dispatcher.io) {
        apiService.getProductsList(productsName = productsName)
    }

    suspend fun getProduct(productId: Int) = withContext(dispatcher.io) {
        apiService.getProduct(productId = productId)
    }
}
