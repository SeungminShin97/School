package com.example.assignment_1.Movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R

class MovieActivity : AppCompatActivity() {
    private lateinit var rv_movie: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_movie)

        val MovieList = arrayListOf(
            Movie(R.drawable.movie1, "블랙 팬서: 와칸다 포에버", "액션", "2022.11.09",161,7.63),
            Movie(R.drawable.movie2, "자백", "범죄", "2022.10.26", 105, 8.19),
            Movie(R.drawable.movie3, "리멤버", "드라마", "2022.10.26", 128, 7.91),
            Movie(R.drawable.movie4, "극장판 짱구는 못말려: 수수께끼! 꽃피는 천하떡잎학교", "애니메이션", "2022.09.28",105, 8.90),
            Movie(R.drawable.movie5,"블랙 아담", "액션", "2022.10.19", 125, 7.60),
            Movie(R.drawable.movie6, "에브리씽 에브리웨어 올 앳 원스", "액션", "2022.10.12", 139, 8.78),
            Movie(R.drawable.movie7, "인생은 아름다워", "뮤지컬", "2022.09.28", 122, 8.35),
            Movie(R.drawable.movie8, "내 친한 친구의 아침식사", "멜로/로맨스", "2022.11.10", 119, 6.86),
            Movie(R.drawable.movie9, "아인보: 아마존의 전설", "애니메이션", "2022.10.26", 84, 7.03),
            Movie(R.drawable.movie10, "공조2: 인터내셔날", "액션", "2022.09.07", 129, 8.01),
            Movie(R.drawable.movie11, "프로메어", "애니메이션", "2022.10.20", 111, 8.77)
        )
        rv_movie = findViewById(R.id.rv_movie)
        rv_movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_movie.setHasFixedSize(true)
        rv_movie.adapter = MovieAdapter(MovieList)
    }
}