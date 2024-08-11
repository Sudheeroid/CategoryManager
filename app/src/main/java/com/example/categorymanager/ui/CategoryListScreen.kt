package com.example.categorymanager.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        DefaultTextFields()
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
                        .clickable {
                            navController.navigate("items/${category.id}")
                        }
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray, //Card background color
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(Color.White),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = category.name.first().toString().uppercase(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Text Content
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = category.name,
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Categories",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }

                            IconButton(onClick = { viewModel.deleteCategory(category) }) {
                                Icon(

                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Go to Category"
                                )
                            }
                        }
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

@Composable
fun DefaultTextFields() {
    CategoryCard("Shopping")
    CategoryCard("Work")
    CategoryCard("Music")
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryListScreen() {
    val navController = rememberNavController()
    CategoryListScreen(navController)
}

@Composable
fun CategoryCard(categoryName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp), colors = CardDefaults.cardColors(
                containerColor = Color.LightGray, //Card background color
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categoryName.first().toString().uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text Content
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Go to Category"
                    )
                }
            }
        }
    }
}


