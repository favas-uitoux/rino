package com.project.myapplication.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.Constants
import com.project.myapplication.R
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.extensions.TextInputEdittextExtension.Companion.getPrice
import com.project.myapplication.extensions.TextInputEdittextExtension.Companion.notEmpty


class AdapterSalez(private val context: Context, private val list: MutableList<StockEntiity>) :
    RecyclerView.Adapter<AdapterSalez.VHClass>() {


    var deposit = 0f
    var indicator = 0


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {


        var txtName: TextView = itemview.findViewById(R.id.txtName)
        var txtDate: TextView = itemview.findViewById(R.id.txtDate)
        var txtQty: TextView = itemview.findViewById(R.id.txtQty)
        var txtPrice: TextView = itemview.findViewById(R.id.txtPrice)
        var txtAmt: TextView = itemview.findViewById(R.id.txtAmt)
        var txtBal: TextView = itemview.findViewById(R.id.txtBal)
        var cns2: ConstraintLayout = itemview.findViewById(R.id.cns2)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.model_trans, parent, false)
        return VHClass(view)
    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {
        val cpr = list[position]
        holder.txtName.text = cpr.itemName
        holder.txtDate.text = cpr.dat


        if(cpr.itemName.toString().equals("Deposit") || cpr.itemName.toString().equals("Draw")  )
        {
            holder.txtPrice.text = "" + cpr.buyPrice.toString()

            if(cpr.itemName.toString().equals("Deposit"))
            {
                deposit = deposit + ( cpr.buyPrice)
            }
            else
            {
                deposit = deposit - ( cpr.buyPrice)
            }

            holder.txtBal.text = "Bal :" + deposit + "/-"
        }
        else {
            holder.txtQty.text = "Qty :" + cpr.qty.toString()
            if (cpr.buyPrice > 0f) {
                holder.txtPrice.text = "Buy Price" + cpr.buyPrice.toString()
                holder.txtAmt.text = "Total :" + cpr.qty * cpr.buyPrice + "/-"
                deposit = deposit - (cpr.qty * cpr.buyPrice)
            } else {
                holder.txtPrice.text = "Sell Price" + cpr.SellingPrice.toString()
                holder.txtAmt.text = "Total :" + cpr.qty * cpr.SellingPrice + "/-"
                deposit = deposit + (cpr.qty * cpr.SellingPrice)
            }

            holder.txtBal.text = "Bal :" + deposit + "/-"
        }

        holder.cns2.setOnLongClickListener {

var buyPrice=0f
var sellPrice=0f
            var dialog: Dialog = Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_delete_edit);

            var cardDelete = dialog.findViewById<CardView>(R.id.cardDelete)
            var cardSave = dialog.findViewById<CardView>(R.id.cardSave)
            var edtQty =
                dialog.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.edtQty)
            var edtItem =
                dialog.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.edtItem)
            var edtPrice =
                dialog.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.edtPrice)
            var spn = dialog.findViewById<Spinner>(R.id.spn)

            cardDelete.setOnClickListener {
                Constants.homeActivityInterface.delete_txn(cpr.id)
                dialog.dismiss()

            }

            edtItem.setText(cpr.itemName)
            edtQty.setText("" + cpr.qty)
            var list2 = listOf("Buy", "Sell")

            val aa: ArrayAdapter<String> = ArrayAdapter<String>(
                context, R.layout.layout_spinner_dropdown,
                android.R.id.text1, list2
            )
            aa.setDropDownViewResource(R.layout.layout_spinner_dropdown);
            spn.adapter = aa





            if (cpr.buyPrice > 0f) {
                edtPrice.setText("" + cpr.buyPrice)
                spn.setSelection(0)

            } else {
                edtPrice.setText("" + cpr.SellingPrice)
                spn.setSelection(1)


            }




            dialog.show();

            spn?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {


                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    if (spn.selectedItem.toString().equals("Buy")) {

                        buyPrice=edtPrice.text.toString().toFloat()
                        sellPrice=0f

                    } else {

                        buyPrice=0f
                        sellPrice=edtPrice.text.toString().toFloat()
                    }

                }

            }


            cardSave.setOnClickListener {

                if (edtQty.notEmpty() && edtItem.notEmpty() && edtPrice.getPrice() > 0) {


                    var tbl = StockEntiity(
                        cpr.id,
                        cpr.dat,
                        edtItem.text.toString(),

                        edtQty.text.toString().toInt(),
                        buyPrice,
                        sellPrice,
                        ""
                    )
                    Constants.homeActivityInterface.updateDtata(tbl)

                } else {
                    Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_LONG)
                        .show()
                }
                dialog.dismiss()

            }


            //
            false
        }


//        if (cpr.id == indicator) {
//            holder.edtDate.isEnabled = true
//            holder.edtItem.isEnabled = true
//            holder.edtQty.isEnabled = true
//            holder.edtBuyPrice.isEnabled = true
//            holder.edtSellPrice.isEnabled = true
//            holder.btnEdit.text = "Save"
//            holder.ll1.setBackgroundColor(context.getColor(R.color.transparent50))
//        } else {
//            holder.edtDate.isEnabled = false
//            holder.edtItem.isEnabled = false
//            holder.edtQty.isEnabled = false
//            holder.edtBuyPrice.isEnabled = false
//            holder.edtSellPrice.isEnabled = false
//            holder.btnEdit.text = "Edit"
//
//          if (cpr.buyPrice>0f)
//              holder.ll1.setBackgroundColor(context.getColor(R.color.transparent))
//            else
//              holder.ll1.setBackgroundColor(context.getColor(R.color.teal_700))
//        }


//
//


//        holder.btnEdit.setOnClickListener(View.OnClickListener {
//
//            if (holder.btnEdit.text.toString().trim().toLowerCase().equals("save")) {
//
//
//                if (holder.edtDate.text.toString().trim()
//                        .equals("") || holder.edtItem.text.toString()
//                        .trim()
//                        .equals("") || holder.edtQty.text.toString().trim().equals("")
//                ) {
//                    Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_LONG)
//                        .show()
//
//                } else {
//                    if (!holder.edtBuyPrice.text.toString().trim()
//                            .equals("") || !holder.edtSellPrice.text.toString()
//                            .trim().equals("")
//                    ) {
//
//                        var buyPrice = holder.edtBuyPrice.getPrice()
//                        var sellPrice = holder.edtSellPrice.getPrice()
//
//
//                        if (buyPrice > 0)
//                            sellPrice = 0f
//                        else
//                            buyPrice = 0f
//
//
//                        var tbl = StockEntiity(
//                            cpr.id,
//                            holder.edtDate.text.toString(),
//                            holder.edtItem.text.toString(),
//                            holder.edtQty.text.toString().toInt(),
//                            buyPrice,
//                            sellPrice,
//
//                            )
//
//                        Constants.homeActivityInterface.updateDtata(tbl)
//                        holder.btnEdit.text = "Edit"
//                        holder.ll1.setBackgroundColor(context.resources.getColor(R.color.transparent))
//
//                    } else {
//                        Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//            } else {
//                deposit = 5000f
//                indicator = cpr.id
//
//
//
//                notifyDataSetChanged()
//
//
//            }
//
//
//        })


//        holder.btnDelete.setOnClickListener(View.OnClickListener {
//
//            Constants.homeActivityInterface.delete_txn(cpr.id)
//        })

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
