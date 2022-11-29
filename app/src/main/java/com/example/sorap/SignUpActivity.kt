package com.example.sorap

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_button.setOnClickListener{
            if(editText_id.text.isEmpty())
                Toast.makeText(this@SignUpActivity, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
            else if(editText_passwd.text.isEmpty())
                Toast.makeText(this@SignUpActivity, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            else if(editText_passwd.text.toString()!=editText_passwdConfirm.text.toString())
                Toast.makeText(this@SignUpActivity, "비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
            else{
                //회원가입 성공, DB에 회원정보 저장
                Toast.makeText(this@SignUpActivity, "회원가입 완료되었습니다.", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
            cancel.setOnClickListener{
                onBackPressed()

            }
        }

    }

}