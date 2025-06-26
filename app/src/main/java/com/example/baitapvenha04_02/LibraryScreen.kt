package com.example.baitapvenha04_02

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.baitapvenha04_02.model.Book
import com.example.baitapvenha04_02.viewmodel.LibraryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel()) {
    var studentName by remember { mutableStateOf("") }
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    val availableBooks = viewModel.getAvailableBooks()
    val selectedStudent by viewModel.selectedStudent
    val notFoundStudent = viewModel.notFoundStudent



    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Quản lý") },
                    label = { Text("Quản lý") },
                    selected = true,
                    onClick = { /* TODO */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = "DS Sách") },
                    label = { Text("DS Sách") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Sinh viên") },
                    label = { Text("Sinh viên") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth() // Bắt buộc để TextAlign.Center hoạt động

            )

            Text("Sinh viên", fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.selectStudent(studentName) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0050C8))
                ) {
                    Text("Thay đổi")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Danh sách sách", fontWeight = FontWeight.SemiBold)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                if (notFoundStudent) {
                    Text(
                        text = "⚠️ Không tìm thấy sinh viên.",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                selectedStudent?.let { student ->
                    Column {
                        Text(
                            "📚 Danh sách sách đã mượn:",
                            style = MaterialTheme.typography.titleMedium
                        )

                        if (student.borrowedBooks.isEmpty()) {
                            Text("Bạn chưa mượn quyển sách nào.")
                        } else {
                            student.borrowedBooks.forEach {
                                Text("- ${it.title}")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (availableBooks.isEmpty()) {
                Text("Không còn sách để mượn.")
            } else {
                Text("📖 Chọn sách để mượn:")
                DropdownMenuSample(
                    books = availableBooks,
                    selectedBook = selectedBook,
                    onBookSelected = { selectedBook = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        selectedBook?.let { viewModel.borrowBook(it) }
                        selectedBook = null
                    },
                    enabled = selectedBook != null
                ) {
                    Text("Thêm")
                }
            }
        }

    }
}
    @Composable
    fun DropdownMenuSample(
        books: List<Book>,
        selectedBook: Book?,
        onBookSelected: (Book) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        Box {
            Text(
                text = selectedBook?.title ?: "Chọn sách...",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .background(Color.White)
                    .padding(8.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                books.forEach { book ->
                    DropdownMenuItem(
                        text = { Text(book.title) },
                        onClick = {
                            onBookSelected(book)
                            expanded = false
                        }
                    )
                }
            }
        }
    }


