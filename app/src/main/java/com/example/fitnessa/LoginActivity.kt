package com.example.fitnessa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        Toast.makeText(this, "All data is open! Please do not use personal password!!", Toast.LENGTH_LONG).show()
        Handler().postDelayed({
            Toast.makeText(this, "Thanks for using this app, have a nice day! ^-^", Toast.LENGTH_LONG).show()
        }, 2000)
        fun allButtons(id: View){
            when(id){
                backBtn -> {
                    finish()
                }
                loginBtn2 -> {
                    val serialId = (Math.random() * 1000000).roundToInt().toString()
                    val dataRef = FirebaseDatabase.getInstance().reference
                    val loginID = loginId.text.toString()
                    val loginPWD = loginPassword.text.toString()
                    dataRef.child(serialId).child("Login Credentials").child("ID").setValue(loginID)
                    dataRef.child(serialId).child("Login Credentials").child("Password").setValue(loginPWD)
                    loginComment.text = "You've logged in!"
                    Handler().postDelayed({
                        finish()
                    }, 500)
                }
            }
        }
        val buttons = arrayOf(loginBtn2, backBtn)
        for (i in buttons){
            i.setOnClickListener {
                allButtons(i)
            }
        }
    }
}