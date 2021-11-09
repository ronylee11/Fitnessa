package com.example.fitnessa

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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

                    }, calYear, calMonth, calDay)
                }
            }
        }
        val buttons = arrayOf(bmiBtn, loginBtn, calendarBtn)
        for (i in buttons){
            i.setOnClickListener {
                allButtons(i)
            }
        }
    }
}