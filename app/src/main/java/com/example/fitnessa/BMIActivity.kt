package com.example.fitnessa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_bmiactivity.*

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_bmiactivity)
        fun allButtons(id: View){
            when(id){
                calculateBtn -> {
                    if (weight.text.toString() != "" && height.text.toString() != ""){
                        val weightC = weight.text.toString().toFloat()
                        val heightC = height.text.toString().toFloat()
                        val bmiC = String.format("%.1f", ((weightC / (heightC * heightC)) * 10000)).toDouble()
                        bmi.setText(bmiC.toString())
                        var comment = "You're "
                        if (bmiC < 18.5) {
                            comment += "Underweight!"
                        } else if (bmiC in 18.5..24.9){
                            comment += "Healthy!"
                        } else if (bmiC in 25.0..29.9) {
                            comment += "Overweight!"
                        } else {
                            comment += "Obesity!"
                        }
                        bmiComment.text = comment
                    } else {
                        bmiComment.text = "Empty field!"
                    }
                }
                resetBtn -> {
                    weight.setText("")
                    height.setText("")
                    bmiComment.text = ""
                }
            }
        }
        val buttons = arrayOf(calculateBtn, resetBtn)
        for (i in buttons){
            i.setOnClickListener {
                allButtons(i)
            }
        }


    }
}