package com.workshop.android.kotlin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.workshop.android.kotlin.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.ByteArrayOutputStream

class CameraActivity : AppCompatActivity() {
    val context: Context = this

    val ID_PEMANGGIL = 1
    val ID_PERMISSION_CAMERA = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        buttonCamera.setOnClickListener {
            //fungsi camera
            if(checkPermissionCamera()) {
                ambilImageDariCamera()
            }
        }
    }

    private fun checkPermissionCamera(): Boolean {
        val currentAPIVersion: Int = Build.VERSION.SDK_INT

        if(currentAPIVersion >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(Array<String>(1) { Manifest.permission.CAMERA }, ID_PERMISSION_CAMERA)
                return false
            }
            else{
                return true
            }
        }
        else{
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == ID_PERMISSION_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariCamera()
            }
            else{
                Toast.makeText(context, "Anda harus memberikan ijin akses camera!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ambilImageDariCamera(){
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, ID_PEMANGGIL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == ID_PEMANGGIL){
                showImageFromCamera(data)
            }
        }
    }

    private fun showImageFromCamera(data: Intent?) {
        data?.let {
            var bitmap = data.extras?.get("data") as Bitmap

            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            previewPhoto.setImageBitmap(bitmap)
        }
    }
}
