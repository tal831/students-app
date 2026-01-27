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
import com.example.studentlist.model.Student

class AddStudentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        supportActionBar?.title = "New Student"

        val saveButton: Button = findViewById(R.id.add_student_details_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_details_cancel_button)

        val nameField: EditText = findViewById(R.id.add_student_details_name_edit_view)
        val idField: EditText = findViewById(R.id.add_student_details_id_edit_view)
        val phoneField: EditText = findViewById(R.id.add_student_details_phone_edit_view)
        val addressField: EditText = findViewById(R.id.add_student_details_address_edit_view)
        val checkBoxField: CheckBox = findViewById(R.id.add_student_details_check_box)

        saveButton.setOnClickListener{
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
                Model.share.add(Student(name, id, phone, address, "", isChecked))
                Toast.makeText(this, "Add new Student successfully", Toast.LENGTH_SHORT).show()

                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }
}