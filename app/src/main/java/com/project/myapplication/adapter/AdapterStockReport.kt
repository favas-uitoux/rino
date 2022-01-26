package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.models.modelReport
import com.project.myapplication.models.modelStockReport

class AdapterStockReport(private val context: Context, private val list: MutableList<modelStockReport>) :
    RecyclerView.Adapter<AdapterStockReport.VHClass>() {


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtName: TextView = itemview.findViewById(R.id.txtName)
        var txtTotBuyQty: TextView = itemview.findViewById(R.id.txtTotBuyQty)
        var txtTotSellQty: TextView = itemview.findViewById(R.id.txtTotSellQty)
        var txtSellAvg: TextView = itemview.findViewById(R.id.txtSellAvg)
        var txtBuyAvg: TextView = itemview.findViewById(R.id.txtBuyAvg)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.model_report, parent, false)

        return VHClass(view)

    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {

        val cpr = list[position]
        holder.txtName.setText(""+ cpr.name)



        holder.txtTotBuyQty.setText(""+(cpr.balQty))
        holder.txtBuyAvg.setText(""+cpr.buyAvg)
     //   holder.txtTotSellQty.setText("Sell Qty: " + cpr.totSellQty.toString())







    }

    override fun getItemCount(): Int {
        return list.size
    }


}