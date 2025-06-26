package com.example.baitapvenha04_02.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.baitapvenha04_02.model.Book
import com.example.baitapvenha04_02.model.Student
import com.example.baitapvenha04_02.repository.LibraryRepository

class LibraryViewModel : ViewModel() {

    // Dùng mutableStateOf để Compose tự động cập nhật UI
    private val _selectedStudent = mutableStateOf<Student?>(null)
    val selectedStudent: State<Student?> = _selectedStudent
    var notFoundStudent by mutableStateOf(false)
        private set

    fun selectStudent(name: String) {
        val student = LibraryRepository.studentList.find { it.name == name }
        _selectedStudent.value = student
        notFoundStudent = student == null
    }

    fun borrowBook(book: Book) {
        // Kiểm tra điều kiện
        val student = _selectedStudent.value
        if (!book.isBorrowed && student != null) {
            student.borrowedBooks.add(book)
            book.isBorrowed = true
            // Gán lại giá trị để trigger recomposition
            _selectedStudent.value = student
        }
    }

    fun getAvailableBooks(): List<Book> {
        return LibraryRepository.bookList.filter { !it.isBorrowed }
    }
}
