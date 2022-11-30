package com.example.sorap

import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.sorap.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_signup.*


class SettingActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var sort_type = intent.getStringExtra("sort_type")
        var view_type  = intent.getStringExtra("view_type")
        var auto_classify  = intent.getStringExtra("auto_classify")

        // ActionBar 설정
        setSupportActionBar(toolbar_setting)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // 버튼, 스위치 설정
        //if (auto_classify!!.toInt() == 1) auto_classification_switch.setChecked(true)
        //else auto_classification_switch.setChecked(false)

        logout_button.setOnClickListener {

        }
        delete_account.setOnClickListener{

        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.setting_preferences, rootKey)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.xml.in_right, R.xml.out_right)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


}