package com.myapp.ccounter.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.usecase.GetProductUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    val getProductUseCase: GetProductUseCase,
    val productId: Int
): ViewModel(){
    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadProduct(productId)
    }

    private fun loadProduct(productId: Int){
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val product = getProductUseCase(productId = productId)
                uiState.copy(loading = false, product = product)
            }catch (error: Throwable){
                uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the product"
                )
            }
        }
    }
}

data class DetailScreenState(
    var loading: Boolean = false,
    var product: Product? = null,
    var errorMessage: String? = null
)
