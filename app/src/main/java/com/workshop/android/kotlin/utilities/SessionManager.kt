package com.workshop.android.kotlin.utilities

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    private val KEY_USERNAME = "USERNAME"
    private val KEY_PASSWORD = "PASSWORD"
    private val KEY_LOGIN = "LOGIN"

    protected fun retrieveSharedPreferences(context: Context): SharedPreferences{
        return context.getSharedPreferences("SESSION_MANAGER_V1", Context.MODE_PRIVATE)
    }

    protected fun retrieveSharedPreferencesEditor(context: Context): SharedPreferences.Editor{
        return retrieveSharedPreferences(context).edit()
    }

    //simpan data login
    fun simpanDataLogin(context: Context, userName: String, password: String){
        val editor = retrieveSharedPreferencesEditor(context)

        editor.putString(KEY_USERNAME, userName)
        editor.putString(KEY_PASSWORD, password)
        editor.putBoolean(KEY_LOGIN, true)

        editor.commit()
    }

    //cek login flag
    fun cekLogin(context: Context) : Boolean{
        return retrieveSharedPreferences(context).getBoolean(KEY_LOGIN, false)
    }

    //set login flag
    fun setLoginFlag(context: Context, login: Boolean) {
        val editor = retrieveSharedPreferencesEditor(context)

        editor.putBoolean(KEY_LOGIN, login)
        editor.commit()
    }

    //ambil username
    fun getUserName(context: Context): String? {
        return retrieveSharedPreferences(context).getString(KEY_USERNAME, "")
    }

    //ambil password
    fun getPassword(context: Context): String? {
        return retrieveSharedPreferences(context).getString(KEY_PASSWORD, "")
    }
}