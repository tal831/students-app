package com.example.studentlist

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentlist.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)

        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val index = extras.getInt("studentPosition")

            val startForResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = Intent()
                    setResult(RESULT_OK, intent)
                    var isDeleted = false

                    val resultData = result.data
                    if (resultData != null) {
                        val resultExtras = resultData.extras
                        if (resultExtras != null) {
                            isDeleted = resultExtras.getBoolean("isDeleted", false)
                        }
                    }

                    if (isDeleted) {
                        finish()
                    } else {
                        setDetails(index)
                    }
                }
            }

            val editButton: Button = findViewById(R.id.student_details_edit_button)
            editButton.setOnClickListener {
                val intent = Intent(applicationContext, StudentEditDetailsActivity::class.java)
                intent.putExtra("studentPosition", index)
                startForResult.launch(intent)
            }

            setDetails(index)
        }
    }

    private fun setDetails(index: Int) {
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}