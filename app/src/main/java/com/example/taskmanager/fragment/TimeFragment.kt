package com.example.taskmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTimeBinding


class TimeFragment : Fragment() {
    lateinit var binding: FragmentTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            binding.button.setText("Hoo")
        }

        return binding.root
    }
}