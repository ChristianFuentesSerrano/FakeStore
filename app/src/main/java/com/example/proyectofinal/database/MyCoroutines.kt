package com.example.proyectofinal.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyCoroutines(private val productDao: ProductDao) {
    suspend fun save(product: Product) = withContext(Dispatchers.IO){
        productDao.save(product.toEntity())
    }

    suspend fun delete(product: Product) = withContext(Dispatchers.IO){
        productDao.delete(product.toEntity())
    }

    suspend fun getProducts(): LiveData<List<Product>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(productDao.getProductsFromDatabase().map { eachProductEntity ->
            eachProductEntity.toProduct() })
    }
}