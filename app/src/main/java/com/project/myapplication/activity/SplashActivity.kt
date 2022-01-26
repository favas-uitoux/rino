package com.project.myapplication.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.project.myapplication.R
import maes.tech.intentanim.CustomIntent.customType


class SplashActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_REQUEST_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({ checkPerminssion() }, 3000)



    }


    private fun checkPerminssion() {

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) + ActivityCompat.checkSelfPermission(
                applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE
            )
             != PackageManager.PERMISSION_GRANTED
        ) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@SplashActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    this@SplashActivity, Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {

                requestPermission()


            } else {
                requestPermission()
            }
        }
        else
        {
            goto_next_screen()
        }
    }


    private fun requestPermission() {
        val builder = AlertDialog.Builder(this@SplashActivity)
        builder.setCancelable(false)
        builder.setMessage(
            " Read and Write External" +
                    " Storage permissions are required to do the task."
        )
        builder.setTitle("Please grant these permissions")
        builder.setPositiveButton(
            "OK"
        ) { dialogInterface, i ->
            ActivityCompat.requestPermissions(
                this@SplashActivity, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,

                    ),
                MY_PERMISSIONS_REQUEST_CODE
            )
        }


        builder.setNeutralButton(
            "Cancel"
        ) { dialogInterface, i ->
            // lmain.setVisibility(View.GONE);
        }


        val dialog = builder.create()
        dialog.show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CODE -> {

                // When request is cancelled, the results array are empty
                if (grantResults.size > 0 &&
                    grantResults[0] + grantResults[1] +  grantResults[2]== PackageManager.PERMISSION_GRANTED
                ) {
                    // Permissions are granted

                    Toast.makeText(applicationContext,"Granted",Toast.LENGTH_LONG).show()
                     goto_next_screen()
                } else {

                // Permissions are denied

                    Toast.makeText(applicationContext,"Denied",Toast.LENGTH_LONG).show()

                }

            }

        }

    }


   private fun goto_next_screen()
    {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        customType(this@SplashActivity, "fadein-to-fadeout")
       finish()

   }




}


