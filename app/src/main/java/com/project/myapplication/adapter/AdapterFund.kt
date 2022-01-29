package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.models.modelReport

class AdapterFund(private val context: Context, private val list: MutableList<modelReport>) :
    RecyclerView.Adapter<AdapterFund.VHClass>() {


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txtDate: TextView = itemview.findViewById(R.id.txtDate)
        var txtRemarks: TextView = itemview.findViewById(R.id.txtRemarks)
        var txtAmt: TextView = itemview.findViewById(R.id.txtAmt)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.model_fund, parent, false)

        return VHClass(view)

    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {

        val cpr = list[position]
        holder.txtDate.setText("" + cpr.name)


        holder.txtRemarks.setText(""  + cpr.remarks)


        holder.txtAmt.setText(""  + cpr.buyAvg.toString())


    }

    override fun getItemCount(): Int {
        return list.size
    }


}