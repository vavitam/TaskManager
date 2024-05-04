package com.example.taskmanager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTimeBinding
import java.time.LocalDate


class TimeFragment : Fragment(R.layout.fragment_time) {
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecycleView: RecyclerView
    private lateinit var selectedDate: LocalDate
    lateinit var binding: FragmentTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        initWidget()
    }

    private fun initWidget() {
        binding.recyclerviewCalendar
    }

    fun previousMonthAction(view: View) {}
    fun nextMonthAction(view: View) {}

}