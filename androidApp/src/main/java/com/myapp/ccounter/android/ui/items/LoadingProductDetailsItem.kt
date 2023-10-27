package com.myapp.ccounter.android.ui.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.ccounter.android.MyApplicationTheme
import com.myapp.ccounter.android.ui.effects.shimmerEffect

@Composable
fun LoadingProductDetailsItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .height(220.dp)
                .width(220.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier,
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect()
                            .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp)),
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(260.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect()
        )
    }

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect()
        )
    }

    Spacer(modifier = Modifier.padding(8.dp))

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.padding(2.dp))

            repeat(4) { i ->
                if (i > 0) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.surface
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(160.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmerEffect()
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmerEffect()
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(190.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.surface
            )

            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.surface
            )

            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(260.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Box(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerEffect()
            )
        }
    }
}

@Preview
@Composable
fun LoadingProductDetailsItemTest() {
    MyApplicationTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                LoadingProductDetailsItem()
            }
        }
    }
}
