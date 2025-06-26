package com.example.baitapvenha04_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.baitapvenha04_02.ui.theme.Baitapvenha0402Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitapvenha04_02.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitapvenha0402Theme {
                val viewModel: LibraryViewModel = viewModel()
                LibraryScreen(viewModel = viewModel)
            }
        }
    }
}

