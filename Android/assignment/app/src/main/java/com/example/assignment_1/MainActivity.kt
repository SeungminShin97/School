package com.example.assignment_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.assignment_1.Book.BookActivity
import com.example.assignment_1.Movie.MovieActivity
import com.example.assignment_1.Show.ShowActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btn_home_movie: Button
    private lateinit var btn_home_book: Button
    private lateinit var btn_home_show: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_home_book = findViewById(R.id.btn_home_book)
        btn_home_movie = findViewById(R.id.btn_home_movie)
        btn_home_show = findViewById(R.id.btn_home_show)

        btn_home_movie.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        btn_home_book.setOnClickListener {
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        }

        btn_home_show.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }
}