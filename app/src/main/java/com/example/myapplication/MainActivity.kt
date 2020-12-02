package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btn_date_picker.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                btn_date_picker.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = dateFormat.parse(selectedDate)

                // calculate age in day
                val selectedDateInDay = date!!.time / 86400000
                val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                val currentDateToDay = currentDate!!.time / 86400000
                val ageInDay = currentDateToDay - selectedDateInDay

                // show age in day
                tv_age_in_day.text = ageInDay.toString()

                // calculate age in month
                val ageInMonth = ((ageInDay.toDouble() / 365) * 12).toInt()

                // show age in month
                tv_age_in_month.text = ageInMonth.toString()

                // calculate age in year
                val ageInYear = (ageInMonth / 12)
//                val today = Calendar.getInstance()
//                val currentYear = today.get(Calendar.YEAR)
//                val ageInYear = currentYear - selectedYear

                // show age in year
                tv_age_in_year.text = ageInYear.toString()

                cognitiveInRangeAge(ageInYear)
            }, year, month, day
        )

        datePicker.datePicker.maxDate = Date().time
        datePicker.show()
    }

    private fun cognitiveInRangeAge(age: Int) {
        when (age) {
            in 0..2 -> {
                tv_description.text = getString(R.string.tahap_sensorimotor)
                tv_cognitive.text = getString(R.string.sensorimotor)
                img_age.setImageResource(R.drawable.sensorimotor)
            }
            in 3..7 -> {
                tv_description.text = getString(R.string.tahap_praoperasional)
                tv_cognitive.text = getString(R.string.praoperasional)
                img_age.setImageResource(R.drawable.praoperasional)
            }
            in 8..11 -> {
                tv_description.text = getString(R.string.tahap_operasional_konkret)
                tv_cognitive.text = getString(R.string.operasional_konret)
                img_age.setImageResource(R.drawable.operasional_konkret)
            }
            in 12..18 -> {
                tv_description.text = getString(R.string.tahap_operasional_formal)
                tv_cognitive.text = getString(R.string.operasional_formal)
                img_age.setImageResource(R.drawable.operasional_formal)
            }
            in 18..40 -> {
                tv_description.text = getString(R.string.masa_dewasa_awal)
                tv_cognitive.text = getString(R.string.dewasa_awal)
                img_age.setImageResource(R.drawable.dewasa_awal)
            }
            in 40..60 -> {
                tv_description.text = getString(R.string.masa_dewasa_madya)
                tv_cognitive.text = getString(R.string.dewasa_madya)
                img_age.setImageResource(R.drawable.dewasa_madya)
            }
            in 60..100 -> {
                tv_description.text = getString(R.string.masa_dewasa_akhir)
                tv_description.text = getString(R.string.masa_dewasa_akhir)
                img_age.setImageResource(R.drawable.dewasa_akhir)
            }
            else -> {
                tv_description.text = getString(R.string.teori_masa_hidup)
                tv_cognitive.text = getString(R.string.masa_hidup)
                img_age.setImageResource(R.drawable.teori_hidup)
            }
        }
    }
}
