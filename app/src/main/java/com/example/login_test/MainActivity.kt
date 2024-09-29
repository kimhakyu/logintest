package com.example.login_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val et_id = findViewById<EditText>(R.id.idEditText)
        val et_pw = findViewById<EditText>(R.id.passwordEditText)
        val btn_login = findViewById<Button>(R.id.loginButton)
        val btn_register = findViewById<Button>(R.id.registerButton)

        val intent = Intent(this, subActivity::class.java)

        btn_login.setOnClickListener {
            val id = et_id.text.toString()
            val pw = et_pw.text.toString()
            if(id.isEmpty() && pw.isEmpty()) {
                Toast.makeText(this,"아이디와 비밀번호를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                val adminID = "hagyu0610"
                val adminPW = "12345678"

                if((id == adminID && pw == adminPW) || userList.any {it.id == id && it.password == pw}) {
                   intent.putExtra("id", id)
                    intent.putExtra("pw", pw)

                    Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn_register.setOnClickListener {
            val getId = et_id.text.toString()
            val getPw = et_pw.text.toString()

            userList.add(User(getId, getPw))
            Toast.makeText(this,"회원가입 성공", Toast.LENGTH_SHORT).show()
        }

    }
}