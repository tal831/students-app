package com.example.studentlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studentlist.model.Model

class StudentEditDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_edit_details)

        supportActionBar?.title = "Edit Student Details"

        val saveButton: Button = findViewById(R.id.student_edit_details_save_button)
        val deleteButton: Button = findViewById(R.id.student_edit_details_delete_button)
        val cancelButton: Button = findViewById(R.id.student_edit_details_cancel_button)

        val nameField: EditText = findViewById(R.id.student_edit_details_name_edit_view)
        val idField: EditText = findViewById(R.id.student_edit_details_id_edit_view)
        val phoneField: EditText = findViewById(R.id.student_edit_details_phone_edit_view)
        val addressField: EditText = findViewById(R.id.student_edit_details_address_edit_view)
        val checkBoxField: CheckBox = findViewById(R.id.student_edit_details_check_box)

        val extras = intent.extras
        if (extras != null) {
            val index = extras.getInt("studentPosition")

            val student = Model.share.get(index)

            nameField.setText(student.name)
            idField.setText(student.id)
            phoneField.setText(student.phone)
            addressField.setText(student.address)
            checkBoxField.isChecked = student.isChecked

            cancelButton.setOnClickListener {
                finish()
            }

            saveButton.setOnClickListener {
                val name = nameField.text.toString()
                val id = idField.text.toString()
                val phone = phoneField.text.toString()
                val address = addressField.text.toString()
                val isChecked = checkBoxField.isChecked

                if (name == "") {
                    Toast.makeText(this, "Please write a Name", Toast.LENGTH_SHORT).show()
                } else if (id == "") {
                    Toast.makeText(this, "Please write an ID", Toast.LENGTH_SHORT).show()
                } else if (phone == "") {
                    Toast.makeText(this, "Please write a Phone", Toast.LENGTH_SHORT).show()
                } else if (address == "") {
                    Toast.makeText(this, "Please write an Address", Toast.LENGTH_SHORT).show()
                } else {
                    val currentStudent = Model.share.get(index)
                    currentStudent.name = name
                    currentStudent.id = id
                    currentStudent.phone = phone
                    currentStudent.address = address
                    currentStudent.isChecked = isChecked

                    Toast.makeText(this, "Edited successfully", Toast.LENGTH_SHORT).show()

                    val intent = Intent()
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }

            deleteButton.setOnClickListener {
                Model.share.remove(index)
                val intent = Intent()
                intent.putExtra("isDeleted", true)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}