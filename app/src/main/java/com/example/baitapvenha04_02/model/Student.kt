package com.example.baitapvenha04_02.model

data class Student(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
)