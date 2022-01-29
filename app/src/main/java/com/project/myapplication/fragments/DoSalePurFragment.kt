package com.project.myapplication.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.myapplication.Constants
import com.project.myapplication.R
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.extensions.EditTextExtension.Companion.getPrice
import com.project.myapplication.extensions.EditTextExtension.Companion.notEmpty
import com.project.myapplication.extensions.TextViewExtension.Companion.notEmpty
import com.project.myapplication.utility.utils.Companion.hideKeyboard
import com.project.myapplication.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class DoSalePurFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    private lateinit var view1: View

    // private lateinit var spn: Spinner
    private lateinit var radioBuy: RadioButton
    private lateinit var radioSell: RadioButton
    private lateinit var edtItem: EditText
    private lateinit var edtQty: EditText
    private lateinit var edtPrice: EditText
    private lateinit var txtDate: TextView
    private lateinit var txtSave: TextView
    private lateinit var ivDate: ImageView
    private var buyPrice = 0.0f
    private var sellPrice = 0.0f
    var cal = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_do_sale_pur, container, false)
        Constants.from = "SalezFragment"
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
                txtDate.setText(sdf.format(cal.getTime()))
            }
        }
        txtDate.setOnClickListener(View.OnClickListener {

            DatePickerDialog(
                view1.context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        })


        ivDate.setOnClickListener(View.OnClickListener {

            DatePickerDialog(
                view1.context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        })

        txtSave.setOnClickListener {

//            if (spn.selectedItem.toString().equals("Buy")) {
//
//                buyPrice=edtPrice.text.toString().toFloat()
//                sellPrice=0f
//
//            } else {
//
//                buyPrice=0f
//                sellPrice=edtPrice.text.toString().toFloat()
//            }

            if (radioBuy.isChecked()) {
                buyPrice=edtPrice.text.toString().toFloat()
                sellPrice=0f
            }
            else
            {
                buyPrice=0f
                sellPrice=edtPrice.text.toString().toFloat()
            }



            if (edtItem.notEmpty() && edtQty.notEmpty() && edtPrice.getPrice() > 0 && txtDate.notEmpty()) {
                viewModel.saveData(
                    StockEntiity(
                        0,
                        txtDate.text.toString(),
                        edtItem.text.toString(),
                        edtQty.text.toString().toInt(),
                        buyPrice,
                        sellPrice,
                        ""
                    )
                )

                edtItem.setText("")
                edtQty.setText("")
                edtPrice.setText("")

                Toast.makeText(activity, "Saved", Toast.LENGTH_LONG)
                    .show()

                hideKeyboard()


            } else {
                Toast.makeText(activity, "All fields are mandatory", Toast.LENGTH_LONG)
                    .show()
            }

        }
        return view1

    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        //spn = view1.findViewById(R.id.spn)
        edtItem = view1.findViewById(R.id.edtItem)
        edtQty = view1.findViewById(R.id.edtQty)
        edtPrice = view1.findViewById(R.id.edtPrice)
        txtDate = view1.findViewById(R.id.txtDate)
        txtSave = view1.findViewById(R.id.txtSave)
        ivDate = view1.findViewById(R.id.ivDate)
        radioBuy = view1.findViewById(R.id.radioBuy)
        radioSell = view1.findViewById(R.id.radioSell)

//        var list2 = listOf("Buy", "Sell")
//
//        val aa: ArrayAdapter<String> = ArrayAdapter<String>(
//            view1.context, R.layout.layout_spinner_dropdown,
//            android.R.id.text1, list2
//        )
//        aa.setDropDownViewResource(R.layout.layout_spinner_dropdown);
//        spn.adapter = aa


    }


}