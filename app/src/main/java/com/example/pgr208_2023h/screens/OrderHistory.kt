package com.example.pgr208_2023h.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pgr208_2023h.models.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistory(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())



    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    Text(
                        "Order history:",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("ShoppingCart")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { navController.navigate("") }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            //Button(onClick = { navController.navigate("ShoppingCart") }) {

                //Text(text = "Go to shoppingcart")
            //}
            Spacer(modifier = Modifier.height(8.dp))

           SumCard()


        }
        // ScrollContent(innerPadding, onClick = { navController.navigate("Details")})
    }
}

@Composable
fun SumCard() {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)

    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            Text(
                text = "Tue ct 31 10:23:01 GMT+01:00 2023",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
            Spacer(modifier = Modifier.height(8.dp))
            Column (modifier = Modifier
                .padding(horizontal = 16.dp)
            ){
                Text(
                    text = "1. Annen tekst her",
                    textAlign = TextAlign.End

                    )

                Text(
                    text = "2. Annen tekst her",
                    textAlign = TextAlign.End

                )
                Text(
                    text = "3. Annen tekst her",
                    textAlign = TextAlign.End

                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$204.23 (3 items )",
                    textAlign = TextAlign.End

                )

            }


        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryCard(products: List<Product>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Order Summary",


                modifier = Modifier.padding(bottom = 8.dp)
            )

            // List of purchased products
            for (product in products) {
                PurchasedProductItem(product = product)
            }

            // Divider
            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // Total price
            Text(
                text = "Total Price: $${calculateTotalPrice(products)}",

                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PurchasedProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product details
        Column {
            Text(
                text = product.title,

                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${product.category} - $${product.price}",

            )
        }
    }
}

fun calculateTotalPrice(products: List<Product>): Double {
    return products.sumByDouble { it.price }
}
