package com.example.categorymanager.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.categorymanager.viewmodel.CategoryViewModel

@Composable
fun CategoryListScreen(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    println("MainActivityComponentActivity5")
    val categories = viewModel.categories.collectAsState(initial = emptyList())

    var showDialog = remember { mutableStateOf(false) }
    var newCategoryName = remember { mutableStateOf("") }

    if (categories.value.isEmpty()) {
        Toast.makeText(LocalContext.current, "No categories found", Toast.LENGTH_SHORT).show()
        Log.d("CategoryListScreen", "No categories found")
    } else {
        Log.d("CategoryListScreen", "Categories loaded: ${categories.value.size}")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                showDialog.value = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Category")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(categories.value) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("items/${category.id}")
                        }
                ) {
                    Text(category.name, modifier = Modifier.weight(1f))
                    Button(onClick = { viewModel.deleteCategory(category) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Add New Category") },
            text = {

                TextField(
                    value = newCategoryName.value,
                    onValueChange = { newCategoryName.value = it },
                    label = { Text("Category Name") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.addCategory(newCategoryName.value)
                        newCategoryName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        newCategoryName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCategoryListScreen() {
    val navController = rememberNavController()
    CategoryListScreen(navController)
}
