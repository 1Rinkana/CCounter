package com.myapp.ccounter.android.ui.screens.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.myapp.ccounter.android.ui.items.LoadingProductListItem
import com.myapp.ccounter.android.ui.items.ProductListItem
import com.myapp.ccounter.domain.model.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedProductsScreen(
    modifier: Modifier = Modifier,
    uiState: SavedProductsScreenState,
    navigateToDetail: (Long) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val gridState = rememberLazyGridState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        LargeTopAppBar(
            title = {
                Text(
                    text = "Saved products",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            },
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
            ),
            scrollBehavior = scrollBehavior,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            state = gridState,
            contentPadding = PaddingValues(horizontal = 16.dp),
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
                            product = ProductItem(
                                id = product.id,
                                title = product.title,
                                imageUrl = product.image,
                            ),
                            onProductClick = { navigateToDetail(product.id) },
                        )
                    }
                }
            }
        }
    }
}
