package com.example.btt1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile) // Liên kết XML layout

        // Khai báo thành phần UI
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val imgAvatar = findViewById<ImageView>(R.id.imgAvatar)
        val txtUserName = findViewById<TextView>(R.id.txtUserName)
        val txtLocation = findViewById<TextView>(R.id.txtLocation)
        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)

        // Xử lý nút quay lại
        btnBack.setOnClickListener {
            finish() // Đóng Activity và quay lại màn hình trước
        }

        // Xử lý nút chỉnh sửa hồ sơ
        btnEditProfile.setOnClickListener {
            txtUserName.text = "Tên mới"
            txtLocation.text = "Địa điểm mới"
        }
    }
}