package com.example.baitapvenha04_02.model

data class Book(
    val id: Int,
    val title: String,
    var isBorrowed: Boolean = false
)