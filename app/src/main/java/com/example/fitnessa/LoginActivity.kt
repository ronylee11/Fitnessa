package com.example.fitnessa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
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
        }, 500)
        fun allButtons(id: View){
            when(id){
                backBtn -> {
                    finish()
                }
                loginBtn2 -> {
                    val serialId = (Math.random() * 1000000).roundToInt().toString()
                    val dataRef = FirebaseDatabase.getInstance().reference
                    dataRef.child(serialId).child("Login Credentials").child("ID").setValue(loginId.text.toString())
                    dataRef.child(serialId).child("Login Credentials").child("Password").setValue(loginPassword.text.toString())
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