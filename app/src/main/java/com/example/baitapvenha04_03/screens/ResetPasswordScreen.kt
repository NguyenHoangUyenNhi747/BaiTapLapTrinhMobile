package com.example.baitapvenha04_03.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenha04_03.R
import com.example.baitapvenha04_03.viewmodels.AuthViewModel

@Composable
fun ResetPasswordScreen(navController: NavHostController, viewModel: AuthViewModel) {
    var confirm by remember { mutableStateOf("") }

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
            painter = painterResource(id = R.drawable.logo_uth), // Thay bằng tên tệp logo trường bạn
            contentDescription = "Logo",
            modifier = Modifier.height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tên app
        Text("SmartTasks", fontSize = 20.sp, color = Color(0xFF007BFF))

        Spacer(modifier = Modifier.height(24.dp))

        // Tiêu đề
        Text("Create new password", fontSize = 20.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Your new password must be different from previously used password",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nhập mật khẩu mới
        OutlinedTextField(
            value = viewModel.newPassword,
            onValueChange = { viewModel.newPassword = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Xác nhận lại mật khẩu
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Confirm Icon")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Next
        Button(
            onClick = {
                if (viewModel.newPassword == confirm && viewModel.resetPassword()) {
                    navController.navigate("confirm")
                }
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
            Text("Next")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    val dummyViewModel = AuthViewModel().apply {
        newPassword = "mypassword123"
    }
    val dummyNav = rememberNavController()
    ResetPasswordScreen(navController = dummyNav, viewModel = dummyViewModel)
}

