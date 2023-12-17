package com.example.pgr208_2023h.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pgr208_2023h.components.AppNavigationDrawer
import com.example.pgr208_2023h.models.Product
import com.example.pgr208_2023h.viewmodels.FavoriteViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorite(navController: NavController, favoriteViewModel: FavoriteViewModel) {

    AppNavigationDrawer(navController = navController, title = "Favorites") {innerPadding->
        FavoriteScrolling(innerPadding, favorites = favoriteViewModel.FavoriteItems,  onItemClick = { navController.navigate("Details/${it}")})

    }
}

@Composable
fun FavoriteScrolling(innerPadding: PaddingValues, favorites: List<Product>, onItemClick:  (id: Int)->Unit  ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (favorites.isNotEmpty()) {
            items(favorites) { favorite ->
                ProductCard(product = favorite, onClick = {onItemClick(favorite.id)})
            }
        } else {
            // If there are no favorites, display a message
            item {
                FavoriteCard()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteCard() {

        Column(modifier = Modifier
            .padding(horizontal = 16.dp),
        ) {
            Text(
                "Your favorite list is empty!"
            )
        }

}