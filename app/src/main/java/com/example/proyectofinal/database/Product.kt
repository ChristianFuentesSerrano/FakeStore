package com.example.proyectofinal.database

class Product(
    id: Int,
    title: String,
    price: Double,
    description: String,
    category: String,
    image: String
) {
    val id: Int = id
    val title: String = title
    val price: Double = price
    val description: String = description
    val category: String = category
    val image: String = image
}

fun Product.toEntity() = ProductEntity(
    id,
    title,
    price,
    description,
    category,
    image
)