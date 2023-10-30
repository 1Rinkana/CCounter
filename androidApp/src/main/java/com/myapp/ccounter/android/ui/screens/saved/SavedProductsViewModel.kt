package com.myapp.ccounter.android.ui.screens.saved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.domain.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SavedProductsViewModel(
    private val productDataSource: LocalDataSource,
): ViewModel() {
    var uiState by mutableStateOf(SavedProductsScreenState())

    init {
        getAllSavedProducts()
    }

    fun getAllSavedProducts() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val products = productDataSource.getAllProducts()
                delay(1000)
                uiState.copy(
                    loading = false,
                    products = products,
                    loadFinished = true
                )
            } catch (error: Throwable){
                uiState.copy(
                    loading = false,
                    loadFinished = false,
                    errorMessage = "Could not get the saved products"
                )
            }
        }
    }
}

data class SavedProductsScreenState(
    var loading: Boolean = false,
    var products: List<Product> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false,
)
