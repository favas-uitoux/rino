package com.project.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.models.modelReport

class AdapterSearchResult(private val context: Context, private val list: MutableList<StockEntiity>) :
    RecyclerView.Adapter<AdapterSearchResult.VHClass>() {


    class VHClass(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txt1: TextView = itemview.findViewById(R.id.txt1)
        val txt2: TextView = itemview.findViewById(R.id.txt2)
        val txt3: TextView = itemview.findViewById(R.id.txt3)
        val txt4: TextView = itemview.findViewById(R.id.txt4)
        val txt5: TextView = itemview.findViewById(R.id.txt5)
        val txt6: TextView = itemview.findViewById(R.id.txt6)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {

        val view = LayoutInflater.from(context).inflate(R.layout.model_search, parent, false)

        return VHClass(view)

    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {

        val cpr = list[position]
        holder.txt1.setText(""+ cpr.dat)
        holder.txt2.setText(""+ cpr.itemName)
        holder.txt3.setText(""+ cpr.qty)

        holder.txt4.setText(""+ cpr.buyPrice)
        holder.txt5.setText(""+ cpr.SellingPrice)
     //   holder.txt5.setText(""+ cpr.s)
        holder.txt6.setText(""+ cpr.remarks)




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