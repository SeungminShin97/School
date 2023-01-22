package com.example.diarytest.ui.search

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarytest.databinding.FragmentSearchItemBinding
import com.example.diarytest.ui.post.PostData

class SearchViewHolder(val binding: FragmentSearchItemBinding) : RecyclerView.ViewHolder(binding.root)

class SearchAdapter (val dataSet: MutableList<PostData>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SearchViewHolder(FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SearchViewHolder).binding
        binding.searchItemImage.setImageURI(Uri.parse(dataSet!![position].image))
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 600
        holder.itemView.requestLayout()
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0

}