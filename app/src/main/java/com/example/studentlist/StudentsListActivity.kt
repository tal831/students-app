package com.example.studentlist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student
import com.example.studentlist.views.StudentsRecyclerAdapter

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class StudentsListActivity : AppCompatActivity() {
    private var students: MutableList<Student>? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)

        supportActionBar?.title = "Students List"

        students = Model.share.students
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)
        recyclerView.adapter = adapter

        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                adapter.notifyDataSetChanged()
            }
        }

        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, StudentDetailsActivity::class.java)
                intent.putExtra("studentPosition", position)
                startForResult.launch(intent)
            }
        }
    }
}