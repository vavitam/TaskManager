package com.example.taskmanager.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.AccountActivity
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityChangePassBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChangePassActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangePassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSave.setOnClickListener {
            thayDoi()
        }

    }

    private fun thayDoi() {
        val newPass = binding.edtNewPass.text.toString()
        val oldPass = binding.edtOldPass.text.toString()

        binding.progressBar.visibility = View.VISIBLE
        binding.linBackground.visibility = View.GONE

        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            user.updatePassword(newPass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Thay đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show()
                        val i = Intent(this, AccountActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, "Đã xảy ra lỗi khi thay đổi mật khẩu: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.linBackground.visibility = View.GONE
    }
}