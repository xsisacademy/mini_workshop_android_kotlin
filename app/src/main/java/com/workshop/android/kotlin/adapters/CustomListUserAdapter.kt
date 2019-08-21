package com.workshop.android.kotlin.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.workshop.android.kotlin.R
import com.workshop.android.kotlin.models.ModelUser
import com.squareup.picasso.Picasso

class CustomListUserAdapter (val context: Context,
                             val modelUsers: List<ModelUser>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var holder: ViewHolderListUser
        var returnView: View

        if(convertView == null){
            //init
            holder = ViewHolderListUser()
            returnView = inflater.inflate(R.layout.custom_list_user_layout, null)

            //inisialisasi element list layout
            holder.avatar = returnView.findViewById(R.id.avatar) as ImageView
            holder.fullname = returnView.findViewById(R.id.fullname) as TextView
            holder.email = returnView.findViewById(R.id.email) as TextView
            holder.listUserArea = returnView.findViewById(R.id.listUserArea) as LinearLayout
        }
        else{
            holder = convertView.tag as ViewHolderListUser
            returnView = convertView
        }

        //set value
        var urlAvatar = modelUsers[position].avatar
//        Picasso.get().load(urlAvatar).into(holder.avatar)
        Glide.with(context).load(urlAvatar).into(holder.avatar!!)

        var value_fullname = modelUsers[position].first_name+" "+modelUsers[position].last_name
        holder.fullname!!.text = value_fullname

        var value_email = modelUsers[position].email
        holder.email!!.text = value_email



        returnView.setTag(holder)
        return returnView
    }

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        modelUsers?.let {
            return modelUsers.size
        }

        return 0
    }

    inner class ViewHolderListUser{
        var avatar: ImageView? = null
        var fullname: TextView? = null
        var email: TextView? = null
        var listUserArea: LinearLayout? = null
    }
}