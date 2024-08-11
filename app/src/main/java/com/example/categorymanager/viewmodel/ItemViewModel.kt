package com.example.categorymanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorymanager.data.db.ItemDao
import com.example.categorymanager.data.db.ItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemDao: ItemDao
) : ViewModel() {

    fun getItemsForCategory(categoryId: Int): Flow<List<ItemEntity>> {
        return itemDao.getItemsByCategory(categoryId)
    }

    fun addItem(categoryId: Int, name: String) {
        viewModelScope.launch {
            itemDao.insertItem(ItemEntity(categoryId = categoryId, name = name))
        }
    }

    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    fun toggleItemCompletion(item: ItemEntity) {
        viewModelScope.launch {
            val updatedItem = item.copy(isDone = !item.isDone)
            itemDao.insertItem(updatedItem)
        }
    }
}
