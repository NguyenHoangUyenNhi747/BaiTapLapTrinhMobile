package com.example.baitapvenha04_03.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import com.example.baitapvenha04_03.screens.ConfirmScreen
import com.example.baitapvenha04_03.screens.ForgetPasswordScreen
import com.example.baitapvenha04_03.screens.ResetPasswordScreen
import com.example.baitapvenha04_03.screens.VerifyCodeScreen
import com.example.baitapvenha04_03.viewmodels.AuthViewModel

sealed class Screen(val route: String) {
    object Forget : Screen("forget")
    object Verify : Screen("verify")
    object Reset : Screen("reset")
    object Confirm : Screen("confirm")
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    NavHost(navController, startDestination = Screen.Forget.route) {
        composable(Screen.Forget.route) {
            ForgetPasswordScreen(navController, viewModel)
        }
        composable(Screen.Verify.route) {
            VerifyCodeScreen(navController, viewModel)
        }
        composable(Screen.Reset.route) {
            ResetPasswordScreen(navController, viewModel)
        }
        composable(Screen.Confirm.route) {
            ConfirmScreen(navController, viewModel)
        }
    }
}