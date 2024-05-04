package com.example.taskmanager.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R

class CalendarAdapter(var dsDayOfMonth:List<String>, var onClickDay: rvCalendarInterface): RecyclerView.Adapter<CalendarAdapter.calendarViewHolder>() {
    inner class calendarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): calendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_cell,parent,false)
        return calendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dsDayOfMonth.size
    }

    override fun onBindViewHolder(holder: calendarViewHolder, position: Int) {
//        val itemView = dsDayOfMonth[position]

//        holder.dayOfMonth.setText(item)
        holder.dayOfMonth.setText(dsDayOfMonth.get(position))

        holder.itemView.setOnClickListener {
            onClickDay.OnclickDayCalendar(position)
        }
    }
}