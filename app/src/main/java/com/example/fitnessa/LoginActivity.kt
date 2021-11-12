package com.example.fitnessa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.roundToInt

class LoginActivity : AppCompatActivity() {
    private lateinit var dataRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        dataRef = FirebaseDatabase.getInstance().reference

        Toast.makeText(this, "All data is open! Please do not use personal password!!", Toast.LENGTH_LONG).show()
        Handler().postDelayed({
            Toast.makeText(this, "Thanks for using this app, have a nice day! ^-^", Toast.LENGTH_LONG).show()
        }, 2000)
        @SuppressLint("SetTextI18n")
        fun allButtons(id: View){
            when(id){
                backBtn -> {
                    finish()
                }
                loginBtn2 -> {
                    val serialId = (Math.random() * 1000000).roundToInt().toString()
//                    val dataRef = FirebaseDatabase.getInstance().reference
                    val loginID = loginId.text.toString()
                    val loginPWD = loginPassword.text.toString()
                    dataRef.child(serialId).child("Login Credentials").child("ID").setValue(loginID)
                    dataRef.child(serialId).child("Login Credentials").child("Password").setValue(loginPWD)
                    loginComment.text = ("You've logged in!")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("serialId", serialId)
                    intent.putExtra("loginID", loginID)
                    Handler().postDelayed({
                        startActivity(intent)
                    }, 2000)
//                    dataRef.addValueEventListener(getData)
//                    dataRef.addListenerForSingleValueEvent(getData)
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

