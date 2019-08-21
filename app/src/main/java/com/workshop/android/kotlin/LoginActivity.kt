package com.workshop.android.kotlin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.workshop.android.kotlin.utilities.Constanta
import com.workshop.android.kotlin.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            validasiInput()
        }
    }

    fun validasiInput() {
        var userName = inputUsername.text.toString().trim()
        var userPassword = inputPassword.text.toString().trim()

        if(userName.length == 0){
            Toast.makeText(context, "Anda belum mengisi username!", Toast.LENGTH_SHORT).show()
        }
        else if(userPassword.length == 0){
            Toast.makeText(context, "Anda belum mengisi password!", Toast.LENGTH_SHORT).show()
        }
        else{
            //sudah diisi semua
            //step 1: simpan data login
            SessionManager().simpanDataLogin(context, userName, userPassword)

            //step 2: pindah ke activity main
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(Constanta.KEY_EXTRA_USERNAME, userName)
            intent.putExtra(Constanta.KEY_EXTRA_PASSWORD, userPassword)
            startActivity(intent)

            finish()
        }
    }
}
