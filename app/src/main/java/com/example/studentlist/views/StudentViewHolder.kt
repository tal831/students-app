package com.example.studentlist.views

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.OnItemClickListener
import com.example.studentlist.R
import com.example.studentlist.model.Student

class StudentViewHolder(
    view: View,
    listener: OnItemClickListener?
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
            listener?.onItemClick(bindingAdapterPosition)
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