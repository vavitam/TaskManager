package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.taskmanager.databinding.ActivityHomeBinding
import com.example.taskmanager.fragment.GroupFragment
import com.example.taskmanager.fragment.TaskFragment
import com.example.taskmanager.fragment.TimeFragment
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
//        binding.navLeftMenu.itemIconTintList = null

        binding.navView.setNavigationItemSelectedListener(this);
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_nav, R.string.close_nav)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment()).commit()
            binding.navView.setCheckedItem(R.id.navTask)
        }

        replaceFragment(TaskFragment())

//        binding.bottomNavigationView.background = null
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.btmNavTask -> replaceFragment(TaskFragment())
//                R.id.btmNavTime -> replaceFragment(TimeFragment())
//                R.id.btmNavGroup -> replaceFragment(GroupFragment())
//            }
//            true
//        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun taiKhoan() {
        val i = Intent(this, AccountActivity::class.java)
        startActivity(i)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navTask -> replaceFragment(TaskFragment())
            R.id.navTime -> replaceFragment(TimeFragment())
            R.id.navGroup -> replaceFragment(GroupFragment())
            R.id.navAccount -> taiKhoan()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}