package com.example.mvvm.data.model

import androidx.compose.ui.graphics.Color

// (M) - Data class đại diện cho một dự án
data class Project(
    val id: Int,
    val title: String,
    val subtitle: String,
    val color: Color,
    val isFaded: Boolean = false
)

// Dữ liệu giả lập cho màn hình chi tiết
data class ProjectDetail(
    val project: Project,
    val subtasks: List<String>,
    val attachments: List<String>
)