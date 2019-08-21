package com.workshop.android.kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.workshop.android.kotlin.adapters.CustomListUserAdapter
import com.workshop.android.kotlin.models.ModelUser
import com.workshop.android.kotlin.utilities.loadingAnimationAndText
import kotlinx.android.synthetic.main.activity_volley.*

class VolleyActivity : AppCompatActivity() {
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)

        buttonGetList.setOnClickListener {
            getListUserFromAPI()
        }
    }

    private fun getListUserFromAPI() {
        var loading = loadingAnimationAndText("Tunggu sebentar....")
        loading.show()

        val urlAPI = "https://reqres.in/api/users?page=1"

        val requestAPI = JsonObjectRequest(
                            Request.Method.GET,
                            urlAPI,
                            null,
                            Response.Listener {
                                response ->
                                //ini request sukses
                                //parsing
                                loading.dismiss()

                                val jsonArray = response.getJSONArray("data")
                                val sizeArray = jsonArray.length()

                                if(sizeArray > 0){
                                    val modelUser = ArrayList<ModelUser>()

                                    for(n in 0 until sizeArray){
                                        val dataObject = jsonArray.getJSONObject(n)

                                        val model = ModelUser()
                                        model.id = dataObject.getInt("id")
                                        model.email = dataObject.getString("email")
                                        model.first_name = dataObject.getString("first_name")
                                        model.last_name = dataObject.getString("last_name")
                                        model.avatar = dataObject.getString("avatar")

                                        modelUser.add(model)
                                    }

                                    //isi model ke adapter list
                                    isiListAdapter(modelUser)
                                }
                                else{
                                    //kosong
                                    Toast.makeText(context, "Empty list user", Toast.LENGTH_SHORT).show()
                                }
                            },
                            Response.ErrorListener {
                                error ->

                                loading.dismiss()

                                //ini request error
                                Toast.makeText(context,
                                    "Error get list user "+error.message,
                                    Toast.LENGTH_SHORT).show()

                                println("Error get list user "+error.message)
                            }
        )

        Volley.newRequestQueue(context)!!.add(requestAPI)
    }

    private fun isiListAdapter(modelUser: ArrayList<ModelUser>) {
        val adapterListUser = CustomListUserAdapter(context, modelUser)

        listUser.adapter = adapterListUser
    }
}
