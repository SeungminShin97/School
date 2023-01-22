package com.example.diarytest.ui.todolist

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.diarytest.R
import com.example.diarytest.databinding.FragmentTodolistAddBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ToDoListAdd : AppCompatActivity() {
    lateinit var binding: FragmentTodolistAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTodolistAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val todolistColl = db.collection("ToDoList")
        todolistColl.orderBy("title")

        binding.todolistSaveButton.setOnClickListener {
            val title: String = findViewById<EditText?>(R.id.post_add_title).text.toString()
            val detail: String = findViewById<EditText?>(R.id.todolist_add_detail).text.toString()
            val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd||HH:mm:ss")).toString()
            var todolistdata = ToDoListData(date, title, detail)
            todolistColl.document(date).set(todolistdata)
            finish()
        }
    }
}