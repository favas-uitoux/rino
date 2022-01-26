package com.project.myapplication.extensions

import android.widget.EditText
import android.widget.TextView

class TextViewExtension {


    companion object
    {





        fun TextView.notEmpty():Boolean
        {

            if(this.text.toString().trim().equals(""))
                return false
            else
                return true


        }

    }

}