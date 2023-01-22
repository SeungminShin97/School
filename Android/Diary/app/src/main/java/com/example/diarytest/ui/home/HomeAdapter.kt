package com.example.diarytest.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.diarytest.databinding.FragmentHomeBinding
import com.example.diarytest.databinding.FragmentHomeItemBinding
import com.example.diarytest.ui.post.PostData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewHolder(val binding: FragmentHomeItemBinding) : RecyclerView.ViewHolder(binding.root)

class HomeAdapter (val dataSet: MutableList<PostData>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeViewHolder(FragmentHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datasize = itemCount - 1
        val binding = (holder as HomeViewHolder).binding
        binding.homeTitle.text = dataSet!![datasize - position].title.toString()
        binding.homeDate.text = dataSet!![datasize - position].createdAt.toString().substring(0,10)
        binding.homeImage.setImageURI(Uri.parse(dataSet!![datasize - position].image))
        binding.homeTag.text = dataSet!![datasize - position].tag.toString()
        binding.homeText.text = dataSet!![datasize - position].text.toString()
        binding.homeDelete.setOnClickListener {
            val db = Firebase.firestore
            val deldate = dataSet!![position].createdAt
            db.collection("Post").document(deldate).delete()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0
}