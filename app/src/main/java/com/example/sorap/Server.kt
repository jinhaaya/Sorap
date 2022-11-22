package com.example.sorap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.jcraft.jsch.JSch
import com.jcraft.jsch.JSchException
import com.jcraft.jsch.Session
import kotlinx.android.synthetic.main.activity_login.*
import java.net.UnknownHostException
import java.util.*

class Server : AppCompatActivity() {

    public fun login(ID:String, passwd:String, host:String = "43.200.46.130", port:Int = 22) : Boolean{
        var session: Session? = null
        try {
            session = JSch().getSession(ID, host, port)
            session.run {
                setConfig(Properties().apply { this["StrictHostKeyChecking"] = "no" })
                setPassword(passwd)
                connect()
            }
            return true
        }catch (ex: UnknownHostException) {
            // 파일을 전송할 원격 호스트가 유효하지 않을 경우 예외 처리
            return false
        }catch (ex: JSchException) {
            // username 또는 password가 일치하지 않을 경우 예외 처리
            return true
        }
    }

    public fun logout(){

    }

    public fun upload(){

    }

    public fun download(){

    }
}