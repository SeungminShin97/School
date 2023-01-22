package com.example.assignment_1.Movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R

class MovieAdapter(val MovieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_movie)
        val title = itemView.findViewById<TextView>(R.id.tv_movie_title)
        val category = itemView.findViewById<TextView>(R.id.tv_movie_category)
        val date = itemView.findViewById<TextView>(R.id.tv_movie_date)
        val time = itemView.findViewById<TextView>(R.id.tv_movie_time)
        val score = itemView.findViewById<TextView>(R.id.tv_movie_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.image.setImageResource(MovieList.get(position).image)
        holder.title.text = MovieList.get(position).title
        holder.category.text = MovieList.get(position).category
        holder.date.text = MovieList.get(position).date
        holder.time.text = MovieList.get(position).time.toString() + "분"
        holder.score.text = "★" + MovieList.get(position).score.toString()
    }

    override fun getItemCount(): Int = MovieList.size
}