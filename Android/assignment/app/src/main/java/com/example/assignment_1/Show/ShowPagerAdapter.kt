package com.example.assignment_1.Show

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R

class ShowPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.iv_show_image)
    fun bind(image: Int) = imageView.setImageResource(image)
}

class ShowPagerAdapter(private val mlist: List<Int>) : RecyclerView.Adapter<ShowPagerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_show_image, parent, false)
        return ShowPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ShowPagerHolder, position: Int) = holder.bind(mlist[position])

    override fun getItemCount(): Int {
        return mlist.size
    }
}