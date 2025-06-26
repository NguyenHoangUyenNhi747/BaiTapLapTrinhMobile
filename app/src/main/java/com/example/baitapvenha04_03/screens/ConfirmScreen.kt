package com.example.baitapvenha04_03.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenha04_03.R
import com.example.baitapvenha04_03.viewmodels.AuthViewModel

@Composable
fun ConfirmScreen(navController: NavHostController, viewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nút quay lại
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logo trường
        Image(
            painter = painterResource(id = R.drawable.logo_uth), // Thay bằng ảnh logo của bạn
            contentDescription = "UTH Logo",
            modifier = Modifier.height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tên app
        Text("SmartTasks", fontSize = 20.sp, color = Color(0xFF007BFF))

        Spacer(modifier = Modifier.height(24.dp))

        // Tiêu đề và mô tả
        Text("Confirm", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text("We are here to help you!", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // Email
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {},
            label = { Text("Email") },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Email")
            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Code
        OutlinedTextField(
            value = viewModel.code,
            onValueChange = {},
            label = { Text("Code") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Code")
            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password
        OutlinedTextField(
            value = viewModel.newPassword,
            onValueChange = {},
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Submit
        Button(
            onClick = {
                // Điều hướng hoặc xử lý sau xác nhận
                navController.navigate("login")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007BFF),
                contentColor = Color.White
            )
        ) {
            Text("Submit")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConfirmScreenPreview() {
    val dummyViewModel = AuthViewModel().apply {
        email = "uth@gmail.com"
        code = "123456"
        newPassword = "password123"
    }
    val dummyNav = rememberNavController()
    ConfirmScreen(navController = dummyNav, viewModel = dummyViewModel)
}
