package com.example.sorap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jcraft.jsch.JSch
import com.jcraft.jsch.JSchException
import com.jcraft.jsch.Session
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 회원가입 버튼
        signin_button.setOnClickListener{
            val intent_signin = Intent(this, SignInActivity::class.java)
            startActivity(intent_signin)
        }

        // 로그인 버튼
        login_button.setOnClickListener{
            val ID = editText_id.text.toString()
            val passwd = editText_passwd.text.toString()
            var session: Session? = null
            try {
                session = JSch().getSession(ID, "43.200.46.130", 22)
                session.setConfig(Properties().apply {
                    this["StrictHostKeyChecking"] = "no"
                })
                session.setPassword(passwd)
                session.connect()
                login_button.text = "success"
                val login_intent = Intent(this, MainActivity::class.java)
                //login_intent.putExtra("ID", ID)
                startActivity(login_intent)
            }catch (ex: JSchException) {
                login_button.text = "FAIL"
                when (ex.message) {
                }
            }
        }
    }
}