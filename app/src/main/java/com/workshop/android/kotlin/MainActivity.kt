package com.workshop.android.kotlin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshop.android.kotlin.utilities.Constanta
import com.workshop.android.kotlin.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tangkapIntentExtra()
        tampilkanUsernameDariSessionManager()

        buttonLogout.setOnClickListener {
            //lakukan proses logout
            //step 1: ganti flag login menjadi false
            SessionManager().setLoginFlag(context, false)

            //step 2: buka screen login
            val intent: Intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

            //step 3: tutup screen main
            finish()
        }

        menu1.setOnClickListener {
            val intent: Intent = Intent(context, CameraActivity::class.java)
            startActivity(intent)
        }

        menu3.setOnClickListener {
            val intent: Intent = Intent(context, EsafirmActivity::class.java)
            startActivity(intent)
        }

        menu2.setOnClickListener {
            val intent: Intent = Intent(context, VolleyActivity::class.java)
            startActivity(intent)
        }

        menu4.setOnClickListener {
            val intent: Intent = Intent(context, DaftarMahasiswaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun tampilkanUsernameDariSessionManager() {
        var valueUserName = SessionManager().getUserName(context)
        var valuePassword = SessionManager().getPassword(context)

        username.text = " " + valueUserName + " " +valuePassword
    }

    fun tangkapIntentExtra(){
        var bundle: Bundle? = intent.extras

        //safe call ?.let{}
        bundle?.let {
            //tidak null
            var valueUserName = bundle!!.getString(Constanta.KEY_EXTRA_USERNAME)
            var valuePassword = bundle!!.getString(Constanta.KEY_EXTRA_PASSWORD)

            username.text = " " + valueUserName + " " +valuePassword
        }
    }
}
