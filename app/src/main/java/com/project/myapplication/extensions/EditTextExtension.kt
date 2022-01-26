package com.project.myapplication.extensions

import android.widget.EditText

class EditTextExtension {


    companion object
    {


        fun EditText.getPrice():Float
        {

            if(this.text.toString().trim().equals(""))
                return 0f
            else
                return this.text.toString().trim().toFloat()
        }



        fun EditText.notEmpty():Boolean
        {

            if(this.text.toString().trim().equals(""))
                return false
            else
                return true


        }

    }

}