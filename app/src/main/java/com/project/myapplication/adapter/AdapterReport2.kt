package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.models.modelReport

class AdapterReport2(private val context: Context, private val list: MutableList<modelReport>) :
    RecyclerView.Adapter<AdapterReport2.VHClass>() {


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
        holder.txtName.setText("" + cpr.name)


//        if(cpr.type==0)
//        {
        holder.txtTotBuyQty.setText(""  + cpr.buyAvg.toString())
    //    holder.txtBuyAvg.setText("" + cpr.buyAvg.toString())
  //      holder.txtTotSellQty.setText("" + cpr.totSellQty.toString())
       // holder.txtSellAvg.setText("" + cpr.sellAvg.toString())

        //       }

        holder.txtTotSellQty.setText(""  + cpr.remarks)


    }

    override fun getItemCount(): Int {
        return list.size
    }


}