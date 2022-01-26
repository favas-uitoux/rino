package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.Constants
import com.project.myapplication.R
import com.project.myapplication.database.entities.StockEntiity



import com.project.myapplication.extensions.EditTextExtension.Companion.getPrice
import com.project.myapplication.models.model1

class AdapterHome(private val context: Context, private val list: MutableList<StockEntiity>) :
    RecyclerView.Adapter<AdapterHome.VHClass>() {


    var deposit = 5000f
    var indicator = 0


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {

        //private val binding = ViewMediaItemBinding.bind(view)
        var edtDate: EditText = itemview.findViewById(R.id.edtDate)
        var edtItem: EditText = itemview.findViewById(R.id.edtItem)
        var edtQty: EditText = itemview.findViewById(R.id.edtQty)
        var edtBuyPrice: EditText = itemview.findViewById(R.id.edtBuyPrice)
        var edtSellPrice: EditText = itemview.findViewById(R.id.edtSellPrice)
        var edtTotal: EditText = itemview.findViewById(R.id.edtTotal)
        var edtBal: EditText = itemview.findViewById(R.id.edtBal)
        var btnDelete: Button = itemview.findViewById(R.id.btnDelete)
        var btnEdit: Button = itemview.findViewById(R.id.btnEdit)
        var ll1: LinearLayout = itemview.findViewById(R.id.ll1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.row_home, parent, false)
        return VHClass(view)
    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {
        val cpr = list[position]
        if (cpr.id == indicator) {
            holder.edtDate.isEnabled = true
            holder.edtItem.isEnabled = true
            holder.edtQty.isEnabled = true
            holder.edtBuyPrice.isEnabled = true
            holder.edtSellPrice.isEnabled = true
            holder.btnEdit.text = "Save"
            holder.ll1.setBackgroundColor(context.getColor(R.color.transparent50))
        } else {
            holder.edtDate.isEnabled = false
            holder.edtItem.isEnabled = false
            holder.edtQty.isEnabled = false
            holder.edtBuyPrice.isEnabled = false
            holder.edtSellPrice.isEnabled = false
            holder.btnEdit.text = "Edit"

          if (cpr.buyPrice>0f)
              holder.ll1.setBackgroundColor(context.getColor(R.color.transparent))
            else
              holder.ll1.setBackgroundColor(context.getColor(R.color.teal_700))
        }




        holder.edtDate.setText(cpr.dat)
        holder.edtItem.setText(cpr.itemName)
        holder.edtQty.setText("" + cpr.qty)
        holder.edtBuyPrice.setText("" + cpr.buyPrice)
        holder.edtSellPrice.setText("" + cpr.SellingPrice)



        if (cpr.buyPrice > 0f) {
            holder.edtTotal.setText("" + cpr.qty * cpr.buyPrice)
            deposit = deposit - (cpr.qty * cpr.buyPrice)
        } else {
            holder.edtTotal.setText("" + cpr.qty * cpr.SellingPrice)
            deposit = deposit + (cpr.qty * cpr.SellingPrice)
        }


        holder.edtBal.setText("" + deposit)





        holder.btnEdit.setOnClickListener(View.OnClickListener {

            if (holder.btnEdit.text.toString().trim().toLowerCase().equals("save")) {


                if (holder.edtDate.text.toString().trim()
                        .equals("") || holder.edtItem.text.toString()
                        .trim()
                        .equals("") || holder.edtQty.text.toString().trim().equals("")
                ) {
                    Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_LONG)
                        .show()

                } else {
                    if (!holder.edtBuyPrice.text.toString().trim()
                            .equals("") || !holder.edtSellPrice.text.toString()
                            .trim().equals("")
                    ) {

                        var buyPrice = holder.edtBuyPrice.getPrice()
                        var sellPrice = holder.edtSellPrice.getPrice()


                        if (buyPrice > 0)
                            sellPrice = 0f
                        else
                            buyPrice = 0f


                        var tbl = StockEntiity(
                            cpr.id,
                            holder.edtDate.text.toString(),
                            holder.edtItem.text.toString(),
                            holder.edtQty.text.toString().toInt(),
                            buyPrice,
                            sellPrice,
                            ""

                            )

                        Constants.homeActivityInterface.updateDtata(tbl)
                        holder.btnEdit.text = "Edit"
                        holder.ll1.setBackgroundColor(context.resources.getColor(R.color.transparent))

                    } else {
                        Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                deposit = 5000f
                indicator = cpr.id


//                var tempID = list[position].id
//                deposit = 5000f
//                for (row: model1 in list) {
//                    if (tempID == row.id)
//                        list[position].flag1 = true
//                    else
//                        list[position].flag1 = false
//                }

                notifyDataSetChanged()

//                holder.edtDate.isEnabled = true
//                holder.edtItem.isEnabled = true
//                holder.edtQty.isEnabled = true
//                holder.edtBuyPrice.isEnabled = true
//                holder.edtSellPrice.isEnabled = true
//                holder.btnEdit.text = "Save"
//                holder.ll1.setBackgroundColor(context.getColor(R.color.transparent50))
            }


        })



        holder.btnDelete.setOnClickListener(View.OnClickListener {

            Constants.homeActivityInterface.delete_txn(cpr.id)
        })

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
