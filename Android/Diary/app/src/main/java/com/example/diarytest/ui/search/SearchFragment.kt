package com.example.diarytest.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diarytest.databinding.FragmentSearchBinding
import com.example.diarytest.ui.post.PostData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: SearchAdapter
    var dataset: MutableList<PostData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataset = mutableListOf()
        adapter = SearchAdapter(dataset)
        val layoutManager = GridLayoutManager(_binding?.searchGridRecycler?.context, 2)
        _binding?.searchGridRecycler?.adapter = adapter
        _binding?.searchGridRecycler?.layoutManager = layoutManager
        _binding?.searchGridRecycler?.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation))
        _binding?.searchGridRecycler?.setHasFixedSize(true)
        _binding?.searchButton?.setOnClickListener {
            search(_binding!!.searchSearch.text.toString())
        }
    }

    fun search(searchWord: String) {
        val db = Firebase.firestore
        db.collection("Post").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            dataset!!.clear()
            for(snapshot in querySnapshot!!.documents) {
                var tag: String = snapshot.get("tag").toString()
                var arr = tag.split("#")
                arr.forEach {
                    if(it.equals(searchWord)) {
                        var item = snapshot.toObject(PostData::class.java)
                        dataset!!.add(item!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataset?.clear()
        val db = Firebase.firestore
        val SearchColl = db.collection("Post")
        SearchColl.get().addOnCompleteListener {
            if(it.isSuccessful) {
                val document = it.result
                if(!document.isEmpty) {
                    val homes = document.toObjects(PostData::class.java)
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