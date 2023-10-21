package com.myapp.ccounter.android.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.ccounter.android.items.LoadingProductListItem
import com.myapp.ccounter.android.items.ProductListItem
import com.myapp.ccounter.domain.model.ProductItem

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchScreenState,
    searchForProduct: () -> Unit,
    navigateToDetail: (ProductItem) -> Unit,
) {
    var searchText by uiState.request

    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        val gridState = rememberLazyGridState()
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    searchText = searchText,
                    placeholderText = "Enter product",
                    onSearchTextChanged = { searchText = it },
                    onDoneClick = { searchForProduct() },
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier,
                state = gridState,
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                when (uiState.loading) {
                    true -> {
                        items(8) {
                            LoadingProductListItem()
                        }
                    }
                    else -> {
                        itemsIndexed(
                            uiState.products,
                            key = { _, product -> product.id },
                        ) {_, product ->
                            ProductListItem(
                                product = product,
                                onProductClick = { navigateToDetail(product) },
                            )
                        }
                    }
                }
            }
        }
    }
}
