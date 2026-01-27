package com.example.studentlist.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.OnItemClickListener
import com.example.studentlist.R
import com.example.studentlist.model.Student

class StudentsRecyclerAdapter(private val students: MutableList<Student>?): RecyclerView.Adapter<StudentViewHolder>() {
    var listener: OnItemClickListener? = null
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