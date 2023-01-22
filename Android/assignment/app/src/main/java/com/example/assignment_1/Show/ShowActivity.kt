package com.example.assignment_1.Show

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment_1.R

class ShowActivity :AppCompatActivity() {
    private lateinit var pager: ViewPager2
    private lateinit var adapter: ShowPagerAdapter
    private var mlist: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        pager = findViewById(R.id.show_viewpager)
        adapter = ShowPagerAdapter(mlist)
        pager.adapter = ShowSlideAdapter(this)

    }
}