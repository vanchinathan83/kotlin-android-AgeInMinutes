package com.vanchi.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vanchi.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dateButton.setOnClickListener {view ->
            clickDatePicker(view, binding)
        }

    }

    fun clickDatePicker(view : View, binding: ActivityMainBinding) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth,selectedDay ->
                    val selectedDate = "${selectedMonth+1}/$selectedDay/$selectedYear"
                    binding.dateText.setText(selectedDate)
                    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                    val selectedDateInMinutes = sdf.parse(selectedDate)!!.time / 60000
                    val currentDateInMinutes = Date()!!.time / 60000
                    val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                    binding.minutesText.setText(ageInMinutes.toString())

                },
                year,
                month,
                day)

        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }
}