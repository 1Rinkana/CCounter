package com.myapp.ccounter.android.ui.screens.saved

import com.myapp.ccounter.domain.model.Product

class SavedProductsViewModel {
}

data class SavedProductsScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var product: List<Product> = listOf(),
    var errorMassage: String? = null,
    var loadFinished: Boolean = false,
)
