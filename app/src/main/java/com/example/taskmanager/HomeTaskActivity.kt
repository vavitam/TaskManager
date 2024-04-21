package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.databinding.ActivityHomeTaskBinding

class HomeTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Sử dụng màu cho icon
        binding.navLeftMenu.itemIconTintList = null

        // Xủ lý menu navigation
        binding.navLeftMenu.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navAccount -> taiKhoan()
                R.id.navImport -> nhiemVuQuanTrong()
                R.id.navTask -> nhiemVu()
                R.id.navFeed -> phanHoi()
                R.id.navSetting -> caiDat()
            }
            true
        }
    }

    private fun caiDat() {
        TODO("Not yet implemented")
    }

    private fun phanHoi() {
        TODO("Not yet implemented")
    }

    private fun nhiemVu() {
        TODO("Not yet implemented")
    }

    private fun nhiemVuQuanTrong() {
        TODO("Not yet implemented")
    }

    private fun taiKhoan() {
        val i = Intent(this,AccountActivity::class.java)
        startActivity(i)
    }

}