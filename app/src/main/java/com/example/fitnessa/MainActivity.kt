package com.example.fitnessa

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.stream.IntStream.range
import android.R.id




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        val calDate = Calendar.getInstance()
        val calYear = calDate.get(Calendar.YEAR)
        val calMonth = calDate.get(Calendar.MONTH)
        val calDay = calDate.get(Calendar.DAY_OF_MONTH)
        fun allButtons(id: View){
            when(id){
                bmiBtn -> {
                    val intent = Intent(this, BMIActivity::class.java)
                    startActivity(intent)
                }
                loginBtn -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                calendarBtn -> {
                    val calendar = DatePickerDialog(this, {view, year, month, dayOfMonth ->
//                        var currentDate = year.toString() + "--" + (month + 1).toString() + "--" + dayOfMonth.toString()
//                        intent.putExtra("currentDate", currentDate)
                    }, calYear, calMonth, calDay)
                    calendar.show()
                }
                calcBtn -> {
                    var x = 0
                    var y = 0
                    var z = 0
                    if (rep1.text.toString() != "" && set1.text.toString() != "" && lbs1.text.toString() != ""){
                        var volume1 = rep1.text.toString().toInt() * set1.text.toString().toInt() * lbs1.text.toString().toInt()
                        vol1.setText(volume1.toString())
                        x += volume1
                    }
                    if (rep2.text.toString() != "" && set2.text.toString() != "" && lbs2.text.toString() != "") {
                        var volume2 = rep2.text.toString().toInt() * set2.text.toString().toInt() * lbs2.text.toString().toInt()
                        vol2.setText(volume2.toString())
                        y += volume2
                    }
                    if (rep3.text.toString() != "" && set3.text.toString() != "" && lbs3.text.toString() != "") {
                        var volume3 = rep3.text.toString().toInt() * set3.text.toString().toInt() * lbs3.text.toString().toInt()
                        vol3.setText(volume3.toString())
                        z += volume3
                    }
                    var totalVol = x + y + z
                    volume.setText(totalVol.toString())
                }
                resetBtn2 -> {
                    var allFields = arrayOf(rep1, rep2, rep3, set1, set2, set3, lbs1, lbs2, lbs3, vol1, vol2, vol3, volume)
                    for (i in allFields){
                        i.setText("")
                    }
                }
            }
        }
        val buttons = arrayOf(bmiBtn, loginBtn, calendarBtn, calcBtn, resetBtn2)
        for (i in buttons){
            i.setOnClickListener {
                allButtons(i)
            }
        }
    }
}
