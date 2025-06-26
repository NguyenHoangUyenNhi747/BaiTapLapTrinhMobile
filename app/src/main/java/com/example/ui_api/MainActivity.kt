package com.example.ui_api

import com.example.ui_api.Home_flow.modol.TaskViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui_api.Home_flow.TaskDetailScreen
import com.example.ui_api.Home_flow.TaskListScreen
import com.example.ui_api.ui.theme.UIAPITheme


class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIAPITheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // Tạo một NavController để quản lý việc điều hướng
    val navController = rememberNavController()

    // NavHost định nghĩa các "đích đến" (màn hình) có thể điều hướng tới
    NavHost(navController = navController, startDestination = "taskList") {

        // Màn hình danh sách công việc
        composable("taskList") {
            TaskListScreen(
                // viewModel() sẽ tự động cung cấp ViewModel cho màn hình này
                onTaskClick = { taskId ->
                    // Khi một task được click, điều hướng đến màn hình chi tiết
                    // và truyền taskId theo
                    navController.navigate("taskDetail/$taskId")
                }
            )
        }

        // Màn hình chi tiết công việc
        composable(
            route = "taskDetail/{taskId}", // Định nghĩa route với một tham số
            arguments = listOf(navArgument("taskId") { type = NavType.IntType }) // Định nghĩa kiểu của tham số
        ) { backStackEntry ->
            // Lấy taskId từ các tham số của route
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1

            TaskDetailScreen(
                taskId = taskId,
                onNavigateBack = {
                    // Hành động khi nhấn nút quay lại trên màn hình chi tiết
                    navController.popBackStack()
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Manhinh (){
    AppNavigation()
}
