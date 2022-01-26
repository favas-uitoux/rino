package com.project.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.adapter.AdapterReport
import com.project.myapplication.adapter.AdapterStockReport
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.models.modelReport
import com.project.myapplication.models.modelStockReport
import com.project.myapplication.viewmodel.ReportViewModel


class StockReportFragment : Fragment() {
    lateinit var viewModel: ReportViewModel
    lateinit var list1: MutableList<StockEntiity>

    lateinit var view1: View
    lateinit var recv1: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_stock_report, container, false);

        init()
        showReport()
        return view1
    }


    private fun showReport() {
        list1 = ArrayList<StockEntiity>()
        viewModel. readInventoryWithoutDepositDraw().observe(this, Observer {
            var totBuyPrice = 0f
            var totSellD = 0f

            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listStockReport: MutableList<modelStockReport> = ArrayList<modelStockReport>()


            var sze = it.size
            var cnt = 0

            for (row: StockEntiity in it) {
                cnt = cnt + 1
                lateinit var name: String
                var buyP: Float
                var sellD: Float

                var q: Int
                var amt: Float




                name = row.itemName
                buyP = row.buyPrice

                q = row.qty

                if (!name.equals(prevName) && !prevName.equals("")) {

                    var balQty=0
                    var buyAvg=0f
                    if(totBuyQty>0)
                    {
                      balQty=  totBuyQty-totSellQty

                      buyAvg= ( totBuyPrice-totSellD)/balQty
                    }


                    listStockReport.add(modelStockReport(prevName, balQty, buyAvg))

                    totBuyQty = 0
                    totSellQty = 0
                    prevName = ""
                    totBuyPrice=0f
                    totSellD=0f


                }



                if (buyP > 0) {
                    amt = q * buyP
                    totBuyPrice=totBuyPrice+amt
                    totBuyQty = totBuyQty + q

                } else {
                    sellD= q*(totBuyPrice/totBuyQty)

                    totSellD=totSellD+sellD
                  //  totSellQty = totSellQty + q
                    totBuyQty=totBuyQty-q;
                    if(totBuyQty==0)
                    {
                        totSellD=0f
                        totBuyPrice=0f;
                    }
                }

                prevName = name


                if (sze == cnt) {


                    var balQty=0
                    var buyAvg=0f
                    if(totBuyQty>0)
                    {
                       // balQty=  totBuyQty-totSellQty

                        buyAvg= ( totBuyPrice-totSellD)/totBuyQty
                    }


                    listStockReport.add(modelStockReport(prevName, totBuyQty, buyAvg))

                    totBuyQty = 0
                    totSellQty = 0
                    prevName = ""
                    totBuyPrice=0f
                    totSellD=0f


                }


            }

            recv1.layoutManager = LinearLayoutManager(activity)
            recv1.adapter = AdapterStockReport(requireActivity(), listStockReport)

        })


    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        recv1 = view1.findViewById(R.id.recv1)
    }


}