package com.myapp.ccounter.data.util

import com.myapp.ccounter.data.remote.ProductRemote
import com.myapp.ccounter.data.remote.ProductsListItemRemote
import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.model.ProductItem

internal fun ProductRemote.toProduct(): Product {
    return Product(
        id = id,
        ingredientList = ingredientList,
        title = title,
        aisle = aisle,
        image = image,
        nutrition = nutrition,
        price = price,
    )
}

internal fun ProductsListItemRemote.toProductsListItem(): ProductItem {
    return ProductItem(
        id = id,
        title = title,
        imageUrl = imageUrl,
    )
}
