package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.model.Model
import com.example.studentlist.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class StudentsListActivity : AppCompatActivity() {
    private var students: MutableList<Student>? = null

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

        val adapter = StudentsRecyclerAdapter(students, object:  OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, StudentDetailsActivity::class.java)
                intent.putExtra("studentPosition", position)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
    }

    class StudentViewHolder(
        view: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        private var student: Student? = null
        private var viewName: TextView? = null
        private var viewId: TextView? = null
        private var isPresent: CheckBox? = null

        init {
            viewName = itemView.findViewById(R.id.student_row_name_text_view)
            viewId = itemView.findViewById(R.id.student_row_id_text_view)
            isPresent = itemView.findViewById(R.id.student_row_check_box)

            isPresent?.apply {
                setOnClickListener {
                    (tag as? Int)?.let { tag ->
                        student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                    }
                }
            }

            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

        fun bind(student: Student?, position: Int) {
            this.student = student
            viewName?.text = student?.name
            viewId?.text = student?.id

            isPresent?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }
    }

    class StudentsRecyclerAdapter(private val students: MutableList<Student>?, private val listener: OnItemClickListener): RecyclerView.Adapter<StudentViewHolder>() {
        override fun getItemCount(): Int = students?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.student_row,
                parent,
                false
            )
            return StudentViewHolder(itemView, listener)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.bind(
                student = students?.get(position),
                position = position
            )
        }
    }
}