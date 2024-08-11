package com.example.categorymanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorymanager.data.db.CategoryDao
import com.example.categorymanager.data.db.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao
) : ViewModel() {

    val categories: Flow<List<CategoryEntity>> = categoryDao.getAllCategories()

    fun addCategory(name: String) {
        viewModelScope.launch {
            categoryDao.insertCategory(CategoryEntity(name = name))
        }
    }

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            categoryDao.deleteCategory(category)
        }
    }
}
