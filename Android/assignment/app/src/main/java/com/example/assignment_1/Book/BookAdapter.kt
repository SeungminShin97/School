package com.example.assignment_1.Book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R

class BookAdapter(val BookList: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_book)
        val title = itemView.findViewById<TextView>(R.id.tv_book_title)
        val author = itemView.findViewById<TextView>(R.id.tv_book_author)
        val publisher = itemView.findViewById<TextView>(R.id.tv_book_publisher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.image.setImageResource(BookList.get(position).image)
        holder.title.text = BookList.get(position).title
        holder.author.text = BookList.get(position).author
        holder.publisher.text = BookList.get(position).publisher
    }

    override fun getItemCount(): Int = BookList.size
}