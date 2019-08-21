package com.workshop.android.kotlin

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.workshop.android.kotlin.adapters.ListMahasiswaAdapter
import com.workshop.android.kotlin.utilities.DatabaseMahasiswaHelper
import kotlinx.android.synthetic.main.activity_daftar_mahasiswa.*

class DaftarMahasiswaActivity : AppCompatActivity() {
    val context: Context = this

    var databaseHelper: DatabaseMahasiswaHelper? = null
    var cursor: Cursor? = null

    var namaMahasiswa: MutableList<String> = ArrayList()
    var idMahasiswa: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mahasiswa)

        buttonInput.setOnClickListener {
            val intent = Intent(context, InputDataMahasiswaActivity::class.java)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listMahasiswa!!.layoutManager = layoutManager

        readDataMahasiswa()
    }

    private fun readDataMahasiswa() {
        databaseHelper = DatabaseMahasiswaHelper(context)

        val db = databaseHelper!!.readableDatabase

        val queryRead = "SELECT * FROM biodata"
        cursor = db.rawQuery(queryRead, null)

        if(cursor!!.count == 0){
            Toast.makeText(context, "Data masih kosong!!!", Toast.LENGTH_SHORT).show()
        }
        else{
            for(c in 0 until cursor!!.count){
                cursor!!.moveToPosition(c)

                var nama = cursor!!.getString(2)
                var id = cursor!!.getInt(0)

                namaMahasiswa.add(nama)
                idMahasiswa.add(id)
            }

            tampilkanDataMahasiswa()
        }
    }

    private fun tampilkanDataMahasiswa() {
        println("masuk sini>>> "+namaMahasiswa.size)
        val adapterMahasiswa = ListMahasiswaAdapter(context, namaMahasiswa, idMahasiswa)
        listMahasiswa!!.adapter = adapterMahasiswa

        adapterMahasiswa!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }

    private fun refreshList() {
        namaMahasiswa = ArrayList()
        idMahasiswa = ArrayList()

        readDataMahasiswa()
    }
}
