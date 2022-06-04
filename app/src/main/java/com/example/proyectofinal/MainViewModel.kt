package com.example.proyectofinal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.database.DatabaseManager
import com.example.proyectofinal.database.MyCoroutines
import com.example.proyectofinal.database.Product
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun saveProduct(product: Product){
        viewModelScope.launch{
            val productDao = DatabaseManager.instance.database.productDao()
            MyCoroutines(productDao).save(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            val productDao = DatabaseManager.instance.database.productDao()
            MyCoroutines(productDao).delete(product)
        }
    }

    val savedProducts = MutableLiveData<List<Product>>()
    fun getProducts(){
        viewModelScope.launch {
            val productDao = DatabaseManager.instance.database.productDao()
            savedProducts.value = MyCoroutines(productDao).getProducts().value
        }
    }
}