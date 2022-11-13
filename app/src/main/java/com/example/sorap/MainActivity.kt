package com.example.sorap

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ActionBar 설정
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        // fab 설정
        val fab1 : View = findViewById(R.id.fab)
        //fab1.setOnClickListener(
            // 파일 open
        //)

    }

    override fun onStart() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onStart()
    }


    private var time : Long = 0
    override fun onBackPressed() {
        // 2초내에 2번 Back Key 두번 누를 시 앱 종료
        if(System.currentTimeMillis() - time >= 2000){
            time=System.currentTimeMillis()
            Toast.makeText(applicationContext, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else if(System.currentTimeMillis() - time < 2000){
            finish()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정
        when(item.itemId){
            R.id.action_setting->{
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}