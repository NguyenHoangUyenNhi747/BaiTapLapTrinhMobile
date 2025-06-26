package com.example.baitapvenha04_03.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class AuthViewModel : ViewModel() {
    var email by mutableStateOf("")
    var code by mutableStateOf("")
    var newPassword by mutableStateOf("")

    fun sendVerificationCode() {
        // Gửi mã đến email
    }

    fun verifyCode(): Boolean {
        return code == "123456" // Demo
    }

    fun resetPassword(): Boolean {
        return newPassword.length >= 6
    }
}