package com.project.myapplication.fragments


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.adapter.AdapterHome
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

import com.project.myapplication.extensions.TextInputEdittextExtension.Companion.getPrice


class SalesFragment() : Fragment() {

    lateinit var viewModel: HomeViewModel
    lateinit var edtName:com.google.android.material.textfield.TextInputEditText
    lateinit var edtDate:com.google.android.material.textfield.TextInputEditText
    lateinit var edtQty:com.google.android.material.textfield.TextInputEditText
    lateinit var edtBuyPrice:com.google.android.material.textfield.TextInputEditText
    lateinit var edtSelPrice:com.google.android.material.textfield.TextInputEditText
    lateinit var btnSave: Button
    lateinit var view1:View
    lateinit var adp:AdapterHome
    lateinit var recv1:RecyclerView


    var cal = Calendar.getInstance()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_sales, container, false);

        init()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                //  updateDateInView()

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                edtDate.setText(sdf.format(cal.getTime()))
            }
        }

            edtDate.setOnClickListener(View.OnClickListener {

                DatePickerDialog(
                    view1.context ,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            })

        display()




        btnSave.setOnClickListener(View.OnClickListener {



            if (edtDate.text.toString().trim().equals("") || edtName.text.toString().trim()
                    .equals("") || edtQty.text.toString().trim().equals("")
            ) {
                Toast.makeText(activity, "All fields are mandatory", Toast.LENGTH_LONG)
                    .show()

            } else {
                if (!edtBuyPrice.text.toString().trim().equals("") || !edtSelPrice.text.toString()
                        .trim().equals("")
                ) {
                    var buyPrice = edtBuyPrice.getPrice()
                    var sellPrice = edtSelPrice.getPrice()


                    if (buyPrice > 0)
                        sellPrice = 0f
                    else
                        buyPrice = 0f



                    save(
                        StockEntiity(
                            0,
                            edtDate.text.toString(),
                            edtName.text.toString(),
                            edtQty.text.toString().toInt(),
                            buyPrice,
                            sellPrice,
                            ""
                        )
                    )
                } else {
                    Toast.makeText(
                        activity,
                        "All fields are mandatory",
                        Toast.LENGTH_LONG
                    ).show()

                }


            }


        })



        return view1

    }



        private fun save(tbl: StockEntiity) {
        var cnt = viewModel.saveData(tbl)
        // Toast.makeText(applicationContext, "" + cnt, Toast.LENGTH_LONG).show()

    }


  private fun init() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        edtDate=view1.findViewById(R.id.edtDate)
        edtName=view1.findViewById(R.id.edtName)
        edtQty=view1.findViewById(R.id.edtQty)
        edtBuyPrice=view1.findViewById(R.id.edtBuyPrice)
        edtSelPrice=view1.findViewById(R.id.edtSelPrice)
        btnSave=view1.findViewById(R.id.btnSave)
        recv1=view1.findViewById(R.id.recv1)


    }





    private fun display() {

        viewModel.readData().observe(viewLifecycleOwner, Observer {


            adp = AdapterHome(view1.context, it)
            recv1.layoutManager = LinearLayoutManager(view1.context)
            recv1.adapter = adp
        })


    }


}