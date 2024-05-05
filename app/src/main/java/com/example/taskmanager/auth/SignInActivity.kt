package com.example.taskmanager.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.HomeActivity
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDangNhap.setOnClickListener {
            dangNhaptaiKhoan()
        }

        binding.tvDangKy.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnDangNhap.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val i = Intent(this, HomeActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                    .addOnFailureListener {
                        binding.tvNotice.visibility = View.VISIBLE
                        binding.tvNotce2.visibility = View.GONE
                        Toast.makeText(this, "Sign up Failed", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            }

            binding.progressBar.visibility = View.VISIBLE
            binding.linBackground.visibility = View.GONE
        }

        binding.progressBar.visibility = View.GONE
        binding.linBackground.visibility = View.VISIBLE
    }

    private fun dangNhaptaiKhoan() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
    }
}