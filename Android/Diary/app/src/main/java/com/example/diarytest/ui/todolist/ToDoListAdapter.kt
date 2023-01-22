package com.example.diarytest.ui.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarytest.databinding.FragmentTodolistItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ToDoListViewHolder(val binding: FragmentTodolistItemBinding) : RecyclerView.ViewHolder(binding.root)

class ToDoListAdapter (val dataSet: MutableList<ToDoListData>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ToDoListViewHolder(FragmentTodolistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datasize = itemCount - 1
        val binding = (holder as ToDoListViewHolder).binding
        binding.todolistItemTitle.text = dataSet!![datasize - position].title
        binding.todolistItemCheckbox.setOnClickListener {
            val db = Firebase.firestore
            val deldate = dataSet!![position].createdAt
            db.collection("ToDoList").document(deldate).delete()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

}