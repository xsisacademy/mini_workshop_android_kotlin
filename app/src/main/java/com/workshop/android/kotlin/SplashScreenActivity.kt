package com.workshop.android.kotlin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.workshop.android.kotlin.utilities.Constanta
import com.workshop.android.kotlin.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    val context: Context = this

//    //cara 1 : lateinit (ikuti style java)
//    private lateinit var logoSplash1: ImageView
//
//    //cara 2: lazy init (ikuti style java)
//    private val teksVersi1: TextView by lazy {
//        findViewById(R.id.teksVersi) as TextView
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //code untuk fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)

//        this.logoSplash1 = logoSplash

        //ganti isi teksnya dng cara Kotlin
        teksVersi.text = "Version 2.0"

        //buat timer delay sebelum pindah ke MainActivity
        generateDelay()
    }

    fun generateDelay(){
        var timerTask = object : TimerTask(){
            override fun run() {
                if(SessionManager().cekLogin(context)){
                    //true = sudah login
                    pindahKeMainActivity()
                }
                else{
                    //false = belum login
                    pindahKeLoginActivity()
                }
            }
        }

        var timer: Timer = Timer()
        timer.schedule(timerTask, Constanta.SPLASH_DELAY)
    }

    private fun pindahKeMainActivity() {
        //pindah ke activity main setelah delay
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    private fun pindahKeLoginActivity(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)

        finish()
    }

}
