package com.example.diarytest.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarytest.databinding.FragmentCalendarBinding
import com.example.diarytest.databinding.FragmentCalendarItemBinding
import com.example.diarytest.ui.post.PostData

class CalendarViewHolder(val binding: FragmentCalendarItemBinding) : RecyclerView.ViewHolder(binding.root)

class CalendarAdapter (val dataSet: MutableList<PostData>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CalendarViewHolder(FragmentCalendarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as CalendarViewHolder).binding
        binding.calendarItemTitle.text = dataSet!![position].title
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0

}