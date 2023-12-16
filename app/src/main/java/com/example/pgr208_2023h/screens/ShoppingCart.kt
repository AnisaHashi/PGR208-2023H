package com.example.pgr208_2023h.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pgr208_2023h.data.OrderHistory
import com.example.pgr208_2023h.models.Product
import com.example.pgr208_2023h.services.ShoppingCartService
import com.example.pgr208_2023h.viewmodels.CartViewModel
import com.example.pgr208_2023h.viewmodels.OrderHistoryviewModel
import java.time.LocalDateTime


@Composable
fun ShoppingCartItem(product: Product, onDelete:() -> Unit){

    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )


    {


        // Card
        Row(modifier = Modifier
            .fillMaxWidth(0.9f),
        ) {
            ProductCard(product = product, onClick = {})
        }
        // Button delete
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Localized description"
            )
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCart(navController: NavController, cartViewModel: CartViewModel, orderHistoryviewModel: OrderHistoryviewModel) {

    val orders = orderHistoryviewModel.orders.collectAsState(initial = emptyList()).value

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val shoppingCartService = ShoppingCartService()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Home") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    Text(
                        "Cart",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("Favorite")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite, //CardGiftcard
                            contentDescription = "Localized description",
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("ShoppingCart")
                    }) {

                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Shopping Cart"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {

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
        Column(modifier = Modifier.fillMaxHeight()){
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartViewModel.cartItems) { product ->
                    ShoppingCartItem(product = product, onDelete = {cartViewModel.deleteCartItem(product)})
                }

            }
            
            

           //if(cartViewModel.cartItems.size > 0){


            Button(
                onClick = {
                    val date = LocalDateTime.now()
                    val sum = cartViewModel.cartItems.sumOf { it.price }
                    val antall = cartViewModel.cartItems.size
                    val items = cartViewModel.cartItems.map { it.title }



                 //   val orderHistory= OrderHistory(date = date, items = items, sumPrice = sum, sumItems =  antall)
                    val orderHistory = OrderHistory(date = date, items = items, sumPrice = sum, sumItems =  antall)
                    orderHistoryviewModel.addOrder(orderHistory)

                    cartViewModel.clear()

                    navController.navigate("OrderHistory")

                          },
                modifier = Modifier
                    .fillMaxWidth()


            ) {
                Text("Place order (${cartViewModel.cartItems.sumOf { it.price }})")
            }

            //} else {
              // Text(text = "Handlekurven er tom. ")
            //}




        }
       // ScrollContent(innerPadding, onClick = { navController.navigate("Details")})
    }


}