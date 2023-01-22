package com.example.assignment_1.Show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.assignment_1.R

class ShowFragment :Fragment() {
    private var image: Int? = null
    private var title: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getInt("image")
            title = it.getString("title")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_show_image, container, false)
        view.findViewById<ImageView>(R.id.iv_show_image).setImageResource(image!!)
        return view
    }

    companion object {
        fun newInstance(image: Int, title: String) =
            ShowFragment().apply {
                arguments = Bundle().apply {
                    putInt("image", image)
                    putString("title", title)
                }
            }
    }
}