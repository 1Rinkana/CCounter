package com.myapp.ccounter.domain.usecase

import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.repository.ProductRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductUseCase: KoinComponent {
    private val repository: ProductRepository by inject()

    suspend operator fun invoke(productId: Int): Product {
        return repository.getProduct(productId = productId)
    }
}
