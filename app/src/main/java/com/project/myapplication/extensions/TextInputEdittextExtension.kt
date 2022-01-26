package com.project.myapplication.extensions

import com.google.android.material.textfield.TextInputEditText

class TextInputEdittextExtension {


    companion object
    {


        fun com.google.android.material.textfield.TextInputEditText.getPrice():Float
        {

            if(this.text.toString().trim().equals(""))
                return 0f
            else
                return this.text.toString().trim().toFloat()
        }

        fun com.google.android.material.textfield.TextInputEditText.notEmpty():Boolean
        {

            if(this.text.toString().trim().equals(""))
                return false
            else
              return true


        }



    }



}