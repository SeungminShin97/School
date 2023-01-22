package com.example.diarytest.ui.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diarytest.databinding.FragmentCalendarBinding
import com.example.diarytest.ui.post.PostData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: CalendarAdapter
    var dataset: MutableList<PostData>? = null

    private lateinit var yyy: String
    private lateinit var mmm: String
    private lateinit var ddd: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root
        yyy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"))
        mmm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM"))
        ddd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
        _binding!!.calendarCalendar

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataset = mutableListOf()
        adapter = CalendarAdapter(dataset)
        val layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        _binding?.calendarRecyclerview?.adapter = adapter
        _binding?.calendarRecyclerview?.layoutManager = layoutManager
        _binding?.calendarRecyclerview?.addItemDecoration(DividerItemDecoration(this.activity, LinearLayoutManager.VERTICAL))

        _binding!!.calendarCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            yyy = year.toString()
            mmm = (month + 1).toString()
            ddd = dayOfMonth.toString()
            dataset?.clear()
            val db = Firebase.firestore
            db.collection("Post").get().addOnCompleteListener {
                val selectDate: String = yyy + "-" + mmm + "-" + ddd
                for (document in it.result) {
                    if(document.id.toString().substring(0,10) == selectDate) {
                        val data = document.toObject(PostData::class.java)
                        dataset!!.add(data)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataset?.clear()
        val db = Firebase.firestore
        db.collection("Post").get().addOnCompleteListener {
            val selectDate: String = yyy + "-" + mmm + "-" + ddd
            for (document in it.result) {
                if(document.id.toString().substring(0,10) == selectDate) {
                    val data = document.toObject(PostData::class.java)
                    dataset!!.add(data)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
//    fun update() {
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}