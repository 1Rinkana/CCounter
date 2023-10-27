package com.myapp.ccounter.android.ui.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.ccounter.domain.model.ProductItem
import com.myapp.ccounter.domain.usecase.GetProductsListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getProductsListUseCase: GetProductsListUseCase,
): ViewModel() {
    var uiState by mutableStateOf(SearchScreenState())
    private var searchJob: Job? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("COROUTINE", "Cancel was stopped because of: $throwable")
    }

    fun searchForProducts() {
        try {
            if (searchJob?.isActive == true) {
                searchJob?.cancel()
            }
        } catch (error: Throwable) {
            Log.d("CANCEL", "Cancel was stopped because of: ${error.localizedMessage}")
        }

        searchJob = viewModelScope.launch(SupervisorJob() +  coroutineExceptionHandler) {
            uiState = uiState.copy(
                loading = true
            )

            uiState = try {
                val products = getProductsListUseCase.invoke(uiState.request.value)
                delay(1000)
                uiState.copy(
                    loading = false,
                    loadFinished = products.isEmpty(),
                    products = products,
                )
            } catch (error: Throwable) {
                uiState.copy(
                    loading = false,
                    loadFinished = true,
                    errorMassage = "Could not load products ${error.localizedMessage}",
                )
            }
        }
    }
}

data class SearchScreenState(
    var loading: Boolean = false,
    var request: MutableState<String> = mutableStateOf(""),
    var products: List<ProductItem> = listOf(),
    var errorMassage: String? = null,
    var loadFinished: Boolean = false,
)
