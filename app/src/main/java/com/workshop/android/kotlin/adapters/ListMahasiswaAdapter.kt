package com.workshop.android.kotlin.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.workshop.android.kotlin.DetailMahasiswaActivity
import com.workshop.android.kotlin.R
import com.workshop.android.kotlin.utilities.Constanta
import com.workshop.android.kotlin.viewholder.ViewHolderListMahasiswa

class ListMahasiswaAdapter(val context: Context,
                           val namaMahasiswa: List<String>,
                           val idMahasiswa: List<Int>)
    : RecyclerView.Adapter<ViewHolderListMahasiswa>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListMahasiswa {
       println("masuk onCreate>>>>>")

        val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.custom_list_mahasiswa, parent, false)

        return ViewHolderListMahasiswa(view)
    }

    override fun getItemCount(): Int {
        return namaMahasiswa.size
    }

    override fun onBindViewHolder(holder: ViewHolderListMahasiswa, position: Int) {
        println("masuk onBindViewHolder >>>>"+position)
        val nama = namaMahasiswa!![position]
        holder.setDataMahasiswa(nama, position)

        holder.listMahasiswa.setOnClickListener {
            val pindah = Intent(context, DetailMahasiswaActivity::class.java)
            pindah.putExtra(Constanta.KEY_ID_ROW, idMahasiswa[position])
            context.startActivity(pindah)
        }
    }

}