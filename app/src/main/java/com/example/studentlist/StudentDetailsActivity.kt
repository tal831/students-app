package com.example.studentlist

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studentlist.model.Model

class StudentDetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)

        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val index = extras.getInt("studentPosition")
            if (Model.share.students.size > index && index >= 0) {
                val student = Model.share.students[index]
                val nameField: TextView = findViewById(R.id.student_details_name_text_view)
                val idField: TextView = findViewById(R.id.student_details_id_text_view)
                val phoneField: TextView = findViewById(R.id.student_details_phone_text_view)
                val addressField: TextView = findViewById(R.id.student_details_address_text_view)
                val checkBoxField: CheckBox = findViewById(R.id.student_details_check_box)
                val checkedField: TextView = findViewById(R.id.student_details_check_text_view)

                "Name: ${student.name}".also { nameField.text = it }
                "ID: ${student.id}".also { idField.text = it }
                "Phone: ${student.phone}".also { phoneField.text = it }
                "Address: ${student.address}".also { addressField.text = it }

                if (student.isChecked) {
                    checkBoxField.isChecked = true
                    "Checked".also { checkedField.text = it }
                } else {
                    checkBoxField.isChecked = false
                    "Not Checked".also { checkedField.text = it }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish();
        return true;
    }
}