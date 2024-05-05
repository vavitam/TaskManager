package com.example.taskmanager.auth

import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imageUserAccount.setOnClickListener {
//            pickImage.launch("image/*")
        }

        binding.btnDangKy.setOnClickListener {
            dangKyTaiKhoan()
        }

        binding.tvDangNhap.setOnClickListener {
            dangNhap()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnDangKy.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val name = binding.edtTen.text.toString()
            val phone = binding.edtPhone.text.toString()
            val pass = binding.edtPassword.text.toString()
            val confirmPass = binding.edtRePassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                binding.edtEmail.setError("Email is required")
                return@setOnClickListener
            }

            if (pass.length < 6) {
                binding.edtEmail.setError("Pass is required")
                return@setOnClickListener
            }

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        // upload Profile picture


                        if (it.isSuccessful) {
                            val user = hashMapOf(
                                "email" to email,
                                "name" to name,
                                "phone" to phone
                            )

                            val uid = FirebaseAuth.getInstance().currentUser!!.uid

                            db.collection("users").document(uid).set(user)
                                .addOnCompleteListener {
                                    Toast.makeText(this,"Successfully added", Toast.LENGTH_SHORT).show()
                                }

                            dangNhap()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun dangNhap() {
        val i = Intent(this, SignInActivity::class.java)
        startActivity(i)
    }

    private fun dangKyTaiKhoan() {
        TODO("Not yet implemented")
    }
}