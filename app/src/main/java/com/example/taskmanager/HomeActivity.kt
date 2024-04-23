package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.databinding.ActivityHomeBinding
import com.example.taskmanager.fragment.GroupFragment
import com.example.taskmanager.fragment.TaskFragment
import com.example.taskmanager.fragment.TimeFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
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

        // Xử lí menu bottom navigation
        val timeFrag = TimeFragment()
        val groupFrag = GroupFragment()
        val taskFrag = TaskFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,taskFrag)
            commit()
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.btmNavTime -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout,timeFrag)
                        commit()
                    }
                }

                R.id.btmNavGroup -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout,groupFrag)
                        commit()
                    }
                }

                R.id.btmNavTask -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout,taskFrag)
                        commit()
                    }
                }

                else -> {
                }
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