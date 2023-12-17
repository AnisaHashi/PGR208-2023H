package com.example.pgr208_2023h.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pgr208_2023h.models.Product
import com.example.pgr208_2023h.viewmodels.ProductViewModel
import androidx.compose.runtime.mutableStateListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, productViewModel: ProductViewModel) {
    var query by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<List<Product>>(emptyList()) }


    // Adding a sample product to the list


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search input field
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = Search
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Search button
        Button(
            onClick = {

               val searchResult = productViewModel.products.filter { it.title.lowercase().contains(query) || it.description.lowercase().contains(query) }
                println("abdi1: " + searchResult)
                println("abdi2: " + query)
                println("abdi3: " + productViewModel.products.map { it.title })
                result = searchResult
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result) { product ->
                ProductCard( product, onClick={navController.navigate("Details/${product.id}") })
            }
        }
    }
}
