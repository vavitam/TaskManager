package com.example.taskmanager.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    private var selectedImageUri: Uri? = null

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
            pickImage.launch("image/*")
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
            val uid = FirebaseAuth.getInstance().currentUser!!.uid

            binding.progressBar.visibility = View.VISIBLE
            binding.linearBackground.visibility = View.GONE

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Email nhập sai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass.length < 6) {
                Toast.makeText(this, "Mật khẩu tối thiểu 6 kí tự", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedImageUri == null) {
                Toast.makeText(this, "Ảnh chưa được chọn", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    val storageRef = FirebaseStorage.getInstance().reference
                    val imageRef = storageRef.child("images_user/${uid}.jpg")
                    val uploadTask = imageRef.putFile(selectedImageUri!!)
                    uploadTask.addOnSuccessListener {
                        imageRef.downloadUrl.addOnSuccessListener {downloadUrl ->
                            saveUserToFirestore(pass, email, phone, name, uid, downloadUrl.toString())
                        }
                    } .addOnFailureListener {
                        Toast.makeText(this, "Hinh anh that bai", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }

            binding.progressBar.visibility = View.GONE
            binding.linearBackground.visibility = View.VISIBLE
        }
    }

    private fun saveUserToFirestore(pass: String, email: String, phone: String, name: String, uid: String, downloadUrl: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {task ->
            if (task.isSuccessful) {
                val user = hashMapOf(
                    "email" to email,
                    "name" to name,
                    "phone" to phone,
                    "image" to downloadUrl
                )

                db.collection("users").document(uid).set(user)
                    .addOnCompleteListener {
                        Toast.makeText(this,"Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    }

                dangNhap()
            } else {
                Log.e("REGISTER_ERROR", "Error creating user", task.exception)
                Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            binding.imageUserAccount.setImageURI(it)
            selectedImageUri = it
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