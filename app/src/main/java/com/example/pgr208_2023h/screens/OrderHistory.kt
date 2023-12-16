package com.example.pgr208_2023h.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pgr208_2023h.data.OrderHistory
import com.example.pgr208_2023h.viewmodels.OrderHistoryviewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


// Help functions

fun getDate(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss Y")
    return date.format(formatter)
}


@Composable
fun OrderHistoryCard(orderHistory: OrderHistory) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)

    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            Text(
                text = getDate(orderHistory.date),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column (modifier = Modifier
            .padding(horizontal = 16.dp)
        ){

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                itemsIndexed(orderHistory.items){index, item ->
                    Text(
                        text = "${index+1}. ${item}",
                        maxLines = 1

                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$ ${orderHistory.sumPrice}(${orderHistory.sumItems} items )",



            )

        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistory(navController: NavController, orderHistoryviewModel: OrderHistoryviewModel) {
    val orders = orderHistoryviewModel.orders.collectAsState(initial = emptyList()).value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        val currentDestination = navController.currentDestination?.route

                        if (currentDestination != "Home") {
                            navController.navigate("Home")
                        } else {
                            navController.popBackStack()
                        }
                    }) {
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
                        navController.navigate("Favorite")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("ShoppingCart")
                    }) {

                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("") // Replace with your intended action
                    }) {
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
        Column {
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orders) { orderHistory ->
                    OrderHistoryCard(orderHistory = orderHistory)
                }
            }
        }
    }

/*    Scaffold(
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
        Column {
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orderHistoryviewModel.orderHistoryItems) { orderHistory ->
                    OrderHistoryCard(orderHistory = orderHistory)
                }

            }
        }


    }*/
}