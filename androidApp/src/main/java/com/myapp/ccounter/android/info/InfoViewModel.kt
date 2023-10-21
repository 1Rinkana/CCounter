package com.myapp.ccounter.android.info

import com.myapp.ccounter.domain.model.Product

data class SearchScreenState(
    var loading: Boolean = false,
    var products: Product? = null,
    var errorMassage: String? = null,
    var loadFinished: Boolean = false,
)