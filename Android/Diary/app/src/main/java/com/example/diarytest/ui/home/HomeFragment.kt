package com.example.diarytest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diarytest.databinding.FragmentHomeBinding
import com.example.diarytest.ui.post.PostData
import com.example.diarytest.ui.todolist.ToDoListData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: HomeAdapter
    var dataset: MutableList<PostData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataset = mutableListOf()
        adapter = HomeAdapter(dataset)
        val layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        _binding?.homeRecyclerview?.adapter = adapter
        _binding?.homeRecyclerview?.layoutManager = layoutManager
        _binding?.homeRecyclerview?.addItemDecoration(DividerItemDecoration(this.activity, LinearLayoutManager.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        dataset?.clear()
        val db = Firebase.firestore
        val HomeColl = db.collection("Post")
        HomeColl.get().addOnCompleteListener {
            if(it.isSuccessful) {
                val document = it.result
                if(!document.isEmpty) {
                    val homes = document.toObjects(PostData::class.java)
                    val insertIndex: Int = dataset!!.size
                    homes.forEach {
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