package com.myapp.ccounter.android.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.usecase.GetProductUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(
    val getProductUseCase: GetProductUseCase,
    val productDataSource: LocalDataSource,
    val productId: Int,
): ViewModel(){
    var uiState by mutableStateOf(DetailScreenState())
    private var searchJob: Job? = null

    init {
        loadProduct(productId)
    }

    private fun loadProduct(productId: Int) {
        try {
            if (searchJob?.isActive == true) {
                searchJob?.cancel()
            }
        } catch (error: Throwable) {
            Log.d("CANCEL", "Cancel was stopped because of: ${error.localizedMessage}")
        }

        searchJob = viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val product = productDataSource.getProductById(id = productId.toLong())
                    ?: getProductUseCase(productId = productId)
                delay(1000)
                uiState.copy(
                    loading = false,
                    product = product,
                )
            } catch (error: Throwable){
                uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the product"
                )
            }
        }
    }

    fun saveProduct() {
        viewModelScope.launch {
            uiState = try {
                productDataSource.insertProduct(uiState.product!!)
                uiState.copy(
                    hasProductBeenSaved = true
                )
            } catch (error: Throwable){
                uiState.copy(
                    hasProductBeenSaved = false,
                    errorMessage = "Could not save the product"
                )
            }
        }
    }

    fun deleteProduct() {
        viewModelScope.launch {
            uiState = try {
                productDataSource.deleteProductById(uiState.product!!.id)
                uiState.copy(
                    hasProductBeenSaved = false
                )
            } catch (error: Throwable){
                uiState.copy(
                    hasProductBeenSaved = true,
                    errorMessage = "Could not delete the product"
                )
            }
        }
    }
}

data class DetailScreenState(
    var loading: Boolean = false,
    var product: Product? = null,
    var hasProductBeenSaved: Boolean = false,
    var errorMessage: String? = null,
)
