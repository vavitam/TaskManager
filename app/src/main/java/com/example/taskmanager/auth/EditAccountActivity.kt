package com.example.taskmanager.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityEditAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditAccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditAccountBinding

    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("users").document(userID)
        ref.get().addOnSuccessListener {
            if (it != null) {
                val name = it.data?.get("name")?.toString()
                val phone = it.data?.get("phone")?.toString()

                binding.edtEditName.setText(name)
                binding.edtEditPhone.setText(phone)
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,"Failed!", Toast.LENGTH_SHORT).show()
            }
    }
}