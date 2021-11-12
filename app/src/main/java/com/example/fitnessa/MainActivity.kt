package com.example.fitnessa

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        val calDate = Calendar.getInstance()
        val calYear = calDate.get(Calendar.YEAR)
        val calMonth = calDate.get(Calendar.MONTH)
        val calDay = calDate.get(Calendar.DAY_OF_MONTH)
        val dataRef = FirebaseDatabase.getInstance().reference
        var currentDate = "$calYear--$calMonth--$calDay"
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
                    val calendar = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                        currentDate = ""
                        currentDate += year.toString() + "--" + (month + 1).toString() + "--" + dayOfMonth.toString()
                    }, calYear, calMonth, calDay)
                    calendar.show()
                }
                calcBtn -> {
                    var x = 0
                    var y = 0
                    var z = 0
                    if (rep1.text.toString() != "" && set1.text.toString() != "" && lbs1.text.toString() != ""){
                        val volume1 = rep1.text.toString().toInt() * set1.text.toString().toInt() * lbs1.text.toString().toInt()
                        vol1.text = volume1.toString()
                        x += volume1
                    }
                    if (rep2.text.toString() != "" && set2.text.toString() != "" && lbs2.text.toString() != "") {
                        val volume2 = rep2.text.toString().toInt() * set2.text.toString().toInt() * lbs2.text.toString().toInt()
                        vol2.text = volume2.toString()
                        y += volume2
                    }
                    if (rep3.text.toString() != "" && set3.text.toString() != "" && lbs3.text.toString() != "") {
                        val volume3 = rep3.text.toString().toInt() * set3.text.toString().toInt() * lbs3.text.toString().toInt()
                        vol3.text = volume3.toString()
                        z += volume3
                    }
                    val totalVol = x + y + z
                    volume.text = totalVol.toString()
                }
                resetBtn2 -> {
                    val allFields = arrayOf(rep1, rep2, rep3, set1, set2, set3, lbs1, lbs2, lbs3, vol1, vol2, vol3, volume)
                    for (i in allFields){
                        i.text = ""
                    }
                }
                addDataBtn -> {
                    val serialId = intent.getStringExtra("serialId").toString()
                    dataRef.child(serialId).child(currentDate).child("Total Volume").setValue(volume.text.toString())
                    if (rep1.text.toString() != "" && set1.text.toString() != "" && lbs1.text.toString() != ""){
                        val workout1 = workout1.text.toString() + "(" + rep1.text.toString() + "repx" + set1.text.toString() + "setx" + lbs1.text.toString() + "lbs=" + vol1.text.toString() + ")"
                        dataRef.child(serialId).child(currentDate).child("Workout 1").setValue(workout1)
                    }
                    if (rep2.text.toString() != "" && set2.text.toString() != "" && lbs2.text.toString() != "") {
                        val workout2 = workout2.text.toString() + "(" + rep2.text.toString() + "repx" + set2.text.toString() + "setx" + lbs2.text.toString() + "lbs=" + vol2.text.toString() + ")"
                        dataRef.child(serialId).child(currentDate).child("Workout 2").setValue(workout2)
                    }
                    if (rep3.text.toString() != "" && set3.text.toString() != "" && lbs3.text.toString() != "") {
                        val workout3 = workout3.text.toString() + "(" + rep3.text.toString() + "repx" + set3.text.toString() + "setx" + lbs3.text.toString() + "lbs=" + vol3.text.toString() + ")"
                        dataRef.child(serialId).child(currentDate).child("Workout 3").setValue(workout3)
                    }
                    if (notes.text.toString() != "") {
                        dataRef.child(serialId).child(currentDate).child("Notes").setValue(notes.text.toString())
                    }
                }
            }
        }
        val buttons = arrayOf(bmiBtn, loginBtn, calendarBtn, calcBtn, resetBtn2, addDataBtn)
        for (i in buttons){
            i.setOnClickListener {
                allButtons(i)
            }
        }
        var loginID = intent.getStringExtra("loginID").toString()
        if (loginID == "null") {
            loginID = "LOGIN"
        }
        loginBtn.text = loginID
    }
}
