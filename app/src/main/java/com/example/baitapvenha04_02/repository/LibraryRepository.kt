package com.example.baitapvenha04_02.repository

import com.example.baitapvenha04_02.model.Book
import com.example.baitapvenha04_02.model.Student
object LibraryRepository {
    val bookList = mutableListOf(
        Book(1, "Sách 01"),
        Book(2, "Sách 02"),
        Book(3, "Sách 03"),
        Book(4, "Sách 04")
    )

    val studentList = mutableListOf(
        Student(1, "Nguyen Van A"),
        Student(2, "Nguyen Thi B"),
        Student(3, "Nguyen Van C")
    )
}