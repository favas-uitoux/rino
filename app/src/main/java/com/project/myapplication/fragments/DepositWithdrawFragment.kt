package com.project.myapplication.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.myapplication.R
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.extensions.EditTextExtension.Companion.notEmpty
import com.project.myapplication.extensions.TextViewExtension.Companion.notEmpty
import com.project.myapplication.utility.utils.Companion.hideKeyboard
import com.project.myapplication.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

class DepositWithdrawFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    private lateinit var view1: View
    private lateinit var txtSave: TextView
    private lateinit var txtDate: TextView
    private lateinit var edtPrice: EditText
    private lateinit var ivDate: ImageView
    private lateinit var radioDeposit: RadioButton
    private lateinit var radioDraw: RadioButton
    private lateinit var edtRemarks: EditText
    var cal = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_dep_withdraw, container, false)
        init();


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
            if (edtPrice.notEmpty() && txtDate.notEmpty()) {

                var type = ""

                if (radioDeposit.isChecked()) {
                    // is checked
                    type = "Deposit"
                } else {
                    // not checked
                    type = "Draw"
                }

                viewModel.saveData(
                    StockEntiity(
                        0,
                        txtDate.text.toString(),
                        type,
                        0,
                        edtPrice.text.toString().toFloat(),
                        0f,
                        edtRemarks.text.toString()
                    )
                )

                edtPrice.setText("")
                hideKeyboard()

                Toast.makeText(activity, "Saved", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(activity, "All fields are mandatory", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view1
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        txtSave = view1.findViewById(R.id.txtSave)
        txtDate = view1.findViewById(R.id.txtDate)
        edtPrice = view1.findViewById(R.id.edtPrice)
        ivDate = view1.findViewById(R.id.ivDate)
        radioDeposit = view1.findViewById(R.id.radioDeposit)
        radioDraw = view1.findViewById(R.id.radioDraw)
        edtRemarks = view1.findViewById(R.id.edtRemarks)

    }


}