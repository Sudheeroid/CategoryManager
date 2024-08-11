package com.example.categorymanager.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.categorymanager.ui.theme.CategoryManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        println("MainActivityComponentActivity1")
               setContent {
            CategoryManagerTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Category Manager") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Menu")
                                }
                            },
                        )
                    }
                            ,modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = "categories",
                            Modifier.padding(innerPadding)
                        ) {
                            composable("categories") {
                                CategoryListScreen(navController)

                            }
                            composable("items/{categoryId}") { backStackEntry ->
                                val categoryId =
                                    backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
                                categoryId?.let {
                                    CategoryItemsScreen(navController, it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}