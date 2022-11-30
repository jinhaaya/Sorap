package com.example.sorap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_OPEN_DOCUMENT
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //lateinit var view_type
    //lateinit var sort_type


    @Suppress("DEPRECATION")
    @SuppressLint("UseSupportActionBar", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // login & get ID
        val ID : String? = intent.getStringExtra("ID")
        if(ID == null) {
            val intent_login = Intent(this, LoginActivity::class.java)
            startActivity(intent_login)
        }

        setContentView(R.layout.activity_main)

        // ActionBar 설정
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        // permission request
        getPermission()


        // fab 설정
        val fab1 : View = findViewById(R.id.fab)
        val uri : Uri = Uri.EMPTY
        fab1.setOnClickListener{
            // 파일 open
        openFile()
        }

        // sort, view 버튼

        val a = this.getSharedPreferences("view_type", Context.MODE_PRIVATE)
        R.xml.setting_preferences
        test.text = a.toString()
        var cur_sort_type = 0   // 0 : 날짜순, 1 : 이름순
        var cur_view_type = 0   // 0 : Grid, 1: Horizontal
        if (cur_sort_type == 0) sort_type.setImageResource(R.drawable.ic_baseline_calendar)
        else sort_type.setImageResource(R.drawable.ic_baseline_abc)
        if (cur_view_type == 0) view_type.setImageResource(R.drawable.ic_baseline_grid)
        else view_type.setImageResource(R.drawable.ic_baseline_view_horizontal)

        sort_type.setOnClickListener {
            cur_sort_type = 1 - cur_sort_type
            if (cur_sort_type == 0) sort_type.setImageResource(R.drawable.ic_baseline_calendar)
            else sort_type.setImageResource(R.drawable.ic_baseline_abc)
            viewChanger(cur_view_type, cur_sort_type)
        }
        view_type.setOnClickListener {
            cur_view_type = 1 - cur_view_type
            if (cur_view_type == 0) view_type.setImageResource(R.drawable.ic_baseline_grid)
            else view_type.setImageResource(R.drawable.ic_baseline_view_horizontal)
            viewChanger(cur_view_type, cur_sort_type)
        }

        // Grid/Horizontal View
        viewChanger(cur_view_type, cur_sort_type)

    }



    @RequiresApi(Build.VERSION_CODES.R)
    fun getPermission(){
        val permission = mutableMapOf<String, String>()
        //permission["camera"] = Manifest.permission.CAMERA
        permission["storageRead"] = Manifest.permission.READ_EXTERNAL_STORAGE
        permission["storageWrite"] =  Manifest.permission.WRITE_EXTERNAL_STORAGE
        permission["storageManage"] =  Manifest.permission.MANAGE_EXTERNAL_STORAGE

        // 현재 권한 상태 검사
        val denied = permission.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        val REQUEST_PERMISSIONS = 0x00000001
        if(denied > 0) requestPermissions(permission.values.toTypedArray(), REQUEST_PERMISSIONS)

        if(false) {
            Toast.makeText(applicationContext, "서비스에 필요한 권한입니다.\n권한에 동의해주세요.", Toast.LENGTH_SHORT).show()
            finish()
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정
        when(item.itemId){
            R.id.action_setting->{
                val intent_setting = Intent(this, SettingActivity::class.java)
                startActivity(intent_setting)
                overridePendingTransition(R.xml.in_left, R.xml.out_left)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun openFile() {
        val READ_REQUEST_CODE = 1
        val intent = Intent(ACTION_OPEN_DOCUMENT)

        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val READ_REQUEST_CODE = 1
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                val uri = data.data
                Log.e("uri", uri.toString())
                val filename = getFileName(uri!!)
                test.text = uri.toString()
            }
        }
    }

    @SuppressLint("Range")
    fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }
        if (result == null) {
            result = uri.lastPathSegment
        }
        return result
    }

    fun viewChanger(view_type : Int, sort_type : Int){
        var list = arrayListOf("Title 1", "asd", "123", "가나다", "나다라", "Title 6", "Title 11", "Title 8")
        if (sort_type == 1) list.sort()

        if (view_type == 0){
            var listManager = GridLayoutManager(this, 3)
            var listAdapter = GridViewAdapter(list)

            var recyclerList = recycler_view.apply {
                setHasFixedSize(true)
                layoutManager = listManager
                adapter = listAdapter
            }
        }
        else if (view_type == 1){
            var manager01 = LinearLayoutManager(this)
            var adapter01 = HorizontalViewAdapter(list)

            var RecyclerView01 = recycler_view.apply {
                adapter = adapter01
                layoutManager = manager01
            }
        }
    }
}