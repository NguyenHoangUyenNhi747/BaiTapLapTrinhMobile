import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.datastore.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var themePreferenceManager: ThemePreferenceManager
    private var selectedThemeColor: String = "default_blue" // Giá trị mặc định

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings) // Giả sử bạn có layout này

        themePreferenceManager = ThemePreferenceManager(this)

        // Theo dõi màu theme đã lưu
        lifecycleScope.launch {
            themePreferenceManager.themeColor.collect { color ->
                color?.let {
                    selectedThemeColor = it
                    applyThemeToUI(it) // Áp dụng theme khi khởi động
                    updateSelectionUI(it) // Cập nhật UI để hiển thị lựa chọn
                }
            }
        }

        // Xử lý sự kiện click cho các ô màu
        val blueColorBox = findViewById<View>(R.id.blueColorBox)
        val pinkColorBox = findViewById<View>(R.id.pinkColorBox)
        val darkColorBox = findViewById<View>(R.id.darkColorBox)
        val applyButton = findViewById<View>(R.id.applyButton)

        blueColorBox.setOnClickListener {
            selectedThemeColor = "blue"
            updateSelectionUI(selectedThemeColor)
        }

        pinkColorBox.setOnClickListener {
            selectedThemeColor = "pink"
            updateSelectionUI(selectedThemeColor)
        }

        darkColorBox.setOnClickListener {
            selectedThemeColor = "dark"
            updateSelectionUI(selectedThemeColor)
        }

        applyButton.setOnClickListener {
            lifecycleScope.launch {
                themePreferenceManager.saveThemeColor(selectedThemeColor)
                // Sau khi lưu, bạn có thể thông báo cho các Activity/Fragment khác
                // hoặc recreate Activity hiện tại để áp dụng theme mới
                recreate() // Một cách đơn giản để áp dụng theme cho Activity hiện tại
            }
        }
    }

    private fun updateSelectionUI(color: String) {
        // Xóa tất cả các đường viền
        findViewById<View>(R.id.blueColorBox).setBackgroundResource(0)
        findViewById<View>(R.id.pinkColorBox).setBackgroundResource(0)
        findViewById<View>(R.id.darkColorBox).setBackgroundResource(0)

        // Thêm đường viền cho màu đã chọn
        when (color) {
            "blue" -> findViewById<View>(R.id.blueColorBox).setBackgroundResource(R.drawable.selected_border) // Tạo một drawable cho đường viền
            "pink" -> findViewById<View>(R.id.pinkColorBox).setBackgroundResource(R.drawable.selected_border)
            "dark" -> findViewById<View>(R.id.darkColorBox).setBackgroundResource(R.drawable.selected_border)
        }
    }

    private fun applyThemeToUI(color: String) {
        val rootLayout = findViewById<View>(android.R.id.content) // Lấy root view của Activity
        when (color) {
            "blue" -> rootLayout.setBackgroundColor(Color.parseColor("#ADD8E6")) // Light Blue
            "pink" -> rootLayout.setBackgroundColor(Color.parseColor("#FFC0CB")) // Pink
            "dark" -> rootLayout.setBackgroundColor(Color.parseColor("#363636")) // Dark Grey
            else -> rootLayout.setBackgroundColor(Color.parseColor("#363636")) // Màu mặc định
        }
        // Bạn cũng có thể thay đổi màu của các TextView, Button, v.v. ở đây
        // Ví dụ: findViewById<TextView>(R.id.settingTitle).setTextColor(...)
    }
}