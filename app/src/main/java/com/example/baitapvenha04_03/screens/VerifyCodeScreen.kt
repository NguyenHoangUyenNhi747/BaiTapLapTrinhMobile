package com.example.baitapvenha04_03.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenha04_03.R
import com.example.baitapvenha04_03.viewmodels.AuthViewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun VerifyCodeScreen(navController: NavHostController, viewModel: AuthViewModel) {
    val codeDigits = remember { List(6) { mutableStateOf("") } }
    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nút back
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_uth), // Thay bằng logo của bạn
            contentDescription = "Logo",
            modifier = Modifier
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tên app
        Text("SmartTasks", fontSize = 20.sp, color = Color(0xFF007BFF))

        Spacer(modifier = Modifier.height(16.dp))

        // Tiêu đề và mô tả
        Text("Verify Code", fontSize = 22.sp, color = Color.Black)
        Text(
            "Enter the code\nWe just sent you on your registered Email",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nhập mã xác nhận 6 số
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            codeDigits.forEachIndexed { index, state ->
                OutlinedTextField(
                    value = state.value,
                    onValueChange = {
                        if (it.length <= 1 && it.all { c -> c.isDigit() }) {
                            state.value = it
                            if (it.isNotEmpty()) {
                                // Di chuyển sang ô tiếp theo
                                if (index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                } else {
                                    focusManager.clearFocus() // Ở ô cuối thì bỏ focus
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusRequesters[index]),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Next
        Button(

            onClick = {
                viewModel.code = codeDigits.joinToString("") { it.value }
                if (viewModel.verifyCode()) {
                    navController.navigate("reset")
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
fun VerifyCodeScreenPreview() {
    val dummyViewModel = AuthViewModel().apply {
        code = "123456"
    }
    val dummyNav = rememberNavController()
    VerifyCodeScreen(navController = dummyNav, viewModel = dummyViewModel)
}

