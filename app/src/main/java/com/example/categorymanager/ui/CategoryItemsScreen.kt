package com.example.categorymanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.categorymanager.viewmodel.ItemViewModel

@Composable
fun CategoryItemsScreen(
    navController: NavController,
    categoryId: Int,
    viewModel: ItemViewModel = hiltViewModel()
) {
    val items = viewModel.getItemsForCategory(categoryId).collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Button(
            onClick = { /* Add item logic */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items.value) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            viewModel.toggleItemCompletion(item)
                        }
                ) {
                    Text(item.name, modifier = Modifier.weight(1f))
                    Text(if (item.isDone) "Done" else "Pending")
                    Button(onClick = { viewModel.deleteItem(item) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
