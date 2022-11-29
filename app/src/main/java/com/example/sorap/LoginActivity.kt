package com.example.sorap

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 회원가입 버튼
        signup_button.setOnClickListener{
            val intent_signin = Intent(this, SignUpActivity::class.java)
            startActivity(intent_signin)
        }


        // 로그인 버튼
        login_button.setOnClickListener{
            val ID = editText_id.text.toString()
            val passwd = editText_passwd.text.toString()
            if (true) { //무조건 로그인 가능
                val login_intent = Intent(this, MainActivity::class.java)
                login_intent.putExtra("ID", ID)
                startActivity(login_intent)
            }
            else {
                login_button.text = "fail"
            }
        }

    }

    private var time : Long = 0
    override fun onBackPressed() {
        // 2초내에 2번 Back Key 두번 누를 시 앱 종료
        if(System.currentTimeMillis() - time >= 2000){
            time=System.currentTimeMillis()
            Toast.makeText(applicationContext, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else if(System.currentTimeMillis() - time < 2000){
            finishAffinity()
        }
    }

}