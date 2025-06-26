package com.example.baitapvenha04_03.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.baitapvenha04_03.R
import com.example.baitapvenha04_03.viewmodels.AuthViewModel
import java.util.regex.Pattern
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun ForgetPasswordScreen(navController: NavHostController, viewModel: AuthViewModel) {
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 🔷 Logo trường
        Image(
            painter = painterResource(id = R.drawable.logo_uth), // Thay bằng ảnh logo thật
            contentDescription = "UTH Logo",
            modifier = Modifier.height(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔷 Tên ứng dụng
        Text(
            text = "SmartTasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔷 Tiêu đề và mô tả
        Text("Forget Password?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Enter your Email, we will send you a verification code.", fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // 🔷 Nhập email
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
                showError = false
            },
            label = { Text("Your Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email, // Email icon
                    contentDescription = "Email Icon"
                )
            },
            isError = showError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        if (showError) {
            Text(
                text = "Invalid email format",
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔷 Nút Next
        Button(
            onClick = {
                if (isValidEmail(viewModel.email)) {
                    viewModel.sendVerificationCode()
                    navController.navigate("verify")
                } else {
                    showError = true
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

// ✅ Hàm kiểm tra email hợp lệ
fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    return emailPattern.matcher(email).matches()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForgetPasswordScreenPreview() {
    val dummyViewModel = AuthViewModel()
    val dummyNav = rememberNavController()
    ForgetPasswordScreen(navController = dummyNav, viewModel = dummyViewModel)
}
