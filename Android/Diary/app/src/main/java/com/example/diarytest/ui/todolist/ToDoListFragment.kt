package com.example.diarytest.ui.todolist

import android.content.Intent
import android.os.Bundle
import android.service.autofill.Dataset
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diarytest.MainActivity
import com.example.diarytest.R
import com.example.diarytest.databinding.FragmentTodolistBinding
import com.example.diarytest.databinding.FragmentTodolistItemBinding
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.*

class ToDoListFragment : Fragment() {

    private var _binding: FragmentTodolistBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ToDoListAdapter
    var dataset: MutableList<ToDoListData>? = null
    //private lateinit var checkBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodolistBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //val view = inflater.inflate(R.layout.fragment_todolist_item, container, false)
        //checkBox = view.findViewById(R.id.todolist_item_checkbox)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataset = mutableListOf()
        adapter = ToDoListAdapter(dataset)
        val layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        _binding?.todolistRecyclerView?.adapter = adapter
        _binding?.todolistRecyclerView?.layoutManager = layoutManager
        _binding?.todolistRecyclerView?.addItemDecoration(DividerItemDecoration(this.activity, LinearLayoutManager.VERTICAL))

        _binding!!.todolistAddButton.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ToDoListAdd::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataset?.clear()
        val db = Firebase.firestore
        val TodoColl = db.collection("ToDoList")
        TodoColl.get().addOnCompleteListener {
            if(it.isSuccessful) {
                val document = it.result
                if(!document.isEmpty) {
                    val todos = document.toObjects(ToDoListData::class.java)
                    val insertIndex: Int = dataset!!.size
                    todos.forEach{
                        dataset!!.add(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}