package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.models.modelReport

class AdapterPur(private val context: Context, private val list: MutableList<modelReport>) :
    RecyclerView.Adapter<AdapterPur.VHClass>() {


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtName: TextView = itemview.findViewById(R.id.txtItem)
        var txtTotBuyQty: TextView = itemview.findViewById(R.id.txtQty)
//        var txtTotSellQty: TextView = itemview.findViewById(R.id.txtTotSellQty)
//        var txtSellAvg: TextView = itemview.findViewById(R.id.txtSellAvg)
        var txtBuyAvg: TextView = itemview.findViewById(R.id.txtAvg)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.model_pur_sale, parent, false)

        return VHClass(view)

    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {

        val cpr = list[position]
        holder.txtName.setText(""+ cpr.name)


//        if(cpr.type==0)
//        {
        holder.txtTotBuyQty.setText("Buy Qty: " + cpr.totBuyQty.toString())
        holder.txtBuyAvg.setText("Avg: " + cpr.buyAvg.toString())


        //       }

//        else
//        {
//            holder.txtTotBuyQty.setText("Buy Qty: ")
//            holder.txtBuyAvg.setText("Avg: ")
//            holder.txtTotSellQty.setText("Sell Qty: "+cpr.totQty.toString())
//            holder.txtSellAvg.setText("Avg: "+cpr.avgPrice.toString())
//
//        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


}