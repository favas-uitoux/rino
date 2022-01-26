package com.project.myapplication.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.myapplication.R
import com.project.myapplication.adapter.AdapterReport
import com.project.myapplication.adapter.AdapterReport2
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.models.modelReport
import com.project.myapplication.viewmodel.ReportViewModel



class ReportFragment : Fragment() {
    lateinit var viewModel: ReportViewModel
    lateinit var list1: MutableList<StockEntiity>
    lateinit var adp:AdapterReport
    lateinit var view1:View
    lateinit var recv1:RecyclerView
    lateinit var btnFund: Button
    lateinit var btnPur:Button
    lateinit var btnSale:Button
    lateinit var radioGroup1:RadioGroup
    lateinit var radDeposit:RadioButton
    lateinit var radWithDraw:RadioButton



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1=inflater.inflate(R.layout.fragment_report, container, false);

        init()
        showReport()


        btnPur.setOnClickListener {
            radioGroup1.visibility=View.INVISIBLE
            recv1.adapter=null
            showPur()
        }

        btnFund.setOnClickListener {

            recv1.adapter=null
            radioGroup1.visibility=View.VISIBLE
            radDeposit.isChecked=true
           //
            recv1.adapter=null
            showDeposit()
        }

        btnSale.setOnClickListener {
            radioGroup1.visibility=View.INVISIBLE
            recv1.adapter=null
            showSale()
        }


        radDeposit.setOnClickListener {

                recv1.adapter=null
                showDeposit()



        }

        radWithDraw.setOnClickListener {



            recv1.adapter=null
            showDraw()


        }

        return view1
    }


    private fun showSale()
    {

        list1 = ArrayList<StockEntiity>()
        viewModel.readSale().observe(this, Observer {

            var totBuyPrice = 0f
            var totSellPrice = 0f
            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listReport: MutableList<modelReport> = ArrayList<modelReport>()


            var sze=  it.size
            var cnt=0

            for (row: StockEntiity in it) {
                cnt=cnt+1
                lateinit var name: String
                var buyP: Float
                var sellP: Float
                var q: Int
                var amt: Float



                name = row.itemName
                buyP = row.buyPrice
                sellP = row.SellingPrice
                q = row.qty

                if(!name .equals(prevName) && !prevName.equals("") )
                {

                    if(totBuyQty>0)
                    {
                        var bAvg=totBuyPrice/totBuyQty
                        var sAvg=totSellPrice/totSellQty
                        listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))

                    }





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f




                }




                if (buyP > 0) {
                    amt = q * buyP

                    totBuyPrice=totBuyPrice+amt
                    totBuyQty =totBuyQty +q
                } else {
                    amt = q * sellP

                    totSellPrice=totSellPrice+amt
                    totSellQty=totSellQty+q
                }

                prevName = name




                if(sze==cnt)
                {


                    var bAvg=totBuyPrice/totBuyQty
                    var sAvg=totSellPrice/totSellQty
                    listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f

                }




            }

            recv1.layoutManager= LinearLayoutManager(activity)
            recv1.adapter=AdapterReport(requireActivity(),listReport)

        })



    }

    private fun showPur()
    {

        list1 = ArrayList<StockEntiity>()
        viewModel.readPur().observe(this, Observer {

            var totBuyPrice = 0f
            var totSellPrice = 0f
            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listReport: MutableList<modelReport> = ArrayList<modelReport>()


            var sze=  it.size
            var cnt=0

            for (row: StockEntiity in it) {
                cnt=cnt+1
                lateinit var name: String
                var buyP: Float
                var sellP: Float
                var q: Int
                var amt: Float



                name = row.itemName
                buyP = row.buyPrice
                sellP = row.SellingPrice
                q = row.qty

                if(!name .equals(prevName) && !prevName.equals("") )
                {

                    if(totBuyQty>0)
                    {
                        var bAvg=totBuyPrice/totBuyQty
                        var sAvg=totSellPrice/totSellQty
                        listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))

                    }





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f




                }




                if (buyP > 0) {
                    amt = q * buyP

                    totBuyPrice=totBuyPrice+amt
                    totBuyQty =totBuyQty +q
                } else {
                    amt = q * sellP

                    totSellPrice=totSellPrice+amt
                    totSellQty=totSellQty+q
                }

                prevName = name




                if(sze==cnt)
                {


                    var bAvg=totBuyPrice/totBuyQty
                    var sAvg=totSellPrice/totSellQty
                    listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f

                }




            }

            recv1.layoutManager= LinearLayoutManager(activity)
            recv1.adapter=AdapterReport(requireActivity(),listReport)

        })



    }
    private fun showDraw()
    {


        list1 = ArrayList<StockEntiity>()
        viewModel.readDraw().observe(this, Observer {

            var totBuyPrice = 0f
            var totSellPrice = 0f
            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listReport: MutableList<modelReport> = ArrayList<modelReport>()


            var sze=  it.size
            var cnt=0

            for(row : StockEntiity in it)
            {
                listReport.add(modelReport(row.dat,0,totSellQty,row.buyPrice,0f,row.remarks))
            }



//            for (row: StockEntiity in it) {
//                cnt=cnt+1
//                lateinit var name: String
//                var buyP: Float
//                var sellP: Float
//                var q: Int
//                var amt: Float
//
//
//
//                name = row.itemName
//                buyP = row.buyPrice
//                sellP = row.SellingPrice
//                q = row.qty
//
//                if(!name .equals(prevName) && !prevName.equals("") )
//                {
//
//                    if(totBuyQty>0)
//                    {
//                        var bAvg=totBuyPrice/totBuyQty
//                        var sAvg=totSellPrice/totSellQty
//                        listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg))
//
//                    }
//
//
//
//
//
//                    totBuyQty=0
//                    totSellQty=0
//                    prevName=""
//                    totBuyPrice=0f
//                    totSellPrice=0f
//
//
//
//
//                }
//
//
//
//
//                if (buyP > 0) {
//                    amt = q * buyP
//
//                    totBuyPrice=totBuyPrice+amt
//                    totBuyQty =totBuyQty +q
//                } else {
//                    amt = q * sellP
//
//                    totSellPrice=totSellPrice+amt
//                    totSellQty=totSellQty+q
//                }
//
//                prevName = name
//
//
//
//
//                if(sze==cnt)
//                {
//
//
//                    var bAvg=totBuyPrice/totBuyQty
//                    var sAvg=totSellPrice/totSellQty
//                    listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg))
//
//
//
//
//
//                    totBuyQty=0
//                    totSellQty=0
//                    prevName=""
//                    totBuyPrice=0f
//                    totSellPrice=0f
//
//                }
//
//
//
//
//            }

            recv1.layoutManager= LinearLayoutManager(activity)
            recv1.adapter=AdapterReport2(requireActivity(),listReport)

        })



    }

    private fun showDeposit()
    {

        list1 = ArrayList<StockEntiity>()
        viewModel.readDeposit().observe(this, Observer {

            var totBuyPrice = 0f
            var totSellPrice = 0f
            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listReport: MutableList<modelReport> = ArrayList<modelReport>()


            var sze=  it.size
            var cnt=0

            for(row : StockEntiity in it)
            {
                listReport.add(modelReport(row.dat,0,totSellQty,row.buyPrice,0f,row.remarks))
            }

//            for (row: StockEntiity in it) {
//                cnt=cnt+1
//                lateinit var name: String
//                var buyP: Float
//                var sellP: Float
//                var q: Int
//                var amt: Float
//
//
//                row.dat
//                name = row.itemName
//                buyP = row.buyPrice
//                sellP = row.SellingPrice
//                q = row.qty
//
//                if(!name .equals(prevName) && !prevName.equals("") )
//                {
//
//                    if(totBuyQty>0)
//                    {
//                        var bAvg=totBuyPrice/totBuyQty
//                        var sAvg=totSellPrice/totSellQty
//                        listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg))
//
//                    }
//
//
//
//
//
//                    totBuyQty=0
//                    totSellQty=0
//                    prevName=""
//                    totBuyPrice=0f
//                    totSellPrice=0f
//
//
//
//
//                }
//
//
//
//
//                if (buyP > 0) {
//                    amt = q * buyP
//
//                    totBuyPrice=totBuyPrice+amt
//                    totBuyQty =totBuyQty +q
//                } else {
//                    amt = q * sellP
//
//                    totSellPrice=totSellPrice+amt
//                    totSellQty=totSellQty+q
//                }
//
//                prevName = name
//
//
//
//
//                if(sze==cnt)
//                {
//
//
//                    var bAvg=totBuyPrice/totBuyQty
//                    var sAvg=totSellPrice/totSellQty
//                    listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg))
//
//
//
//
//
//                    totBuyQty=0
//                    totSellQty=0
//                    prevName=""
//                    totBuyPrice=0f
//                    totSellPrice=0f
//
//                }
//
//
//
//
//            }

            recv1.layoutManager= LinearLayoutManager(activity)
            recv1.adapter= AdapterReport2(requireActivity(),listReport)

        })



    }


    private fun showReport() {
        list1 = ArrayList<StockEntiity>()
        viewModel.readInventoryWithoutDepositDraw().observe(this, Observer {

            var totBuyPrice = 0f
            var totSellPrice = 0f
            var prevName = ""
            var totBuyQty = 0
            var totSellQty = 0

            var listReport: MutableList<modelReport> = ArrayList<modelReport>()


          var sze=  it.size
            var cnt=0

            for (row: StockEntiity in it) {
                cnt=cnt+1
                lateinit var name: String
                var buyP: Float
                var sellP: Float
                var q: Int
                var amt: Float



                name = row.itemName
                buyP = row.buyPrice
                sellP = row.SellingPrice
                q = row.qty

                if(!name .equals(prevName) && !prevName.equals("") )
                {

                    if(totBuyQty>0)
                    {
                        var bAvg=totBuyPrice/totBuyQty
                        var sAvg=totSellPrice/totSellQty
                        listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))

                    }





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f




                }




                    if (buyP > 0) {
                        amt = q * buyP

                        totBuyPrice=totBuyPrice+amt
                        totBuyQty =totBuyQty +q
                    } else {
                        amt = q * sellP

                        totSellPrice=totSellPrice+amt
                        totSellQty=totSellQty+q
                    }

                    prevName = name




                if(sze==cnt)
                {


                    var bAvg=totBuyPrice/totBuyQty
                    var sAvg=totSellPrice/totSellQty
                    listReport.add(modelReport(prevName,totBuyQty,totSellQty,bAvg,sAvg,""))





                    totBuyQty=0
                    totSellQty=0
                    prevName=""
                    totBuyPrice=0f
                    totSellPrice=0f

                }




            }

            recv1.layoutManager= LinearLayoutManager(activity)
            recv1.adapter=AdapterReport(requireActivity(),listReport)

        })




    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        recv1=view1.findViewById(R.id.recv1)
        btnFund=view1.findViewById(R.id.btnFund)
        btnPur=view1.findViewById(R.id.btnPur)
        btnSale=view1.findViewById(R.id.btnSale)
        radioGroup1=view1.findViewById(R.id.radioGroup1)
        radioGroup1.visibility=View.INVISIBLE

        radDeposit=view1.findViewById(R.id.radDeposit)
        radWithDraw=view1.findViewById(R.id.radWithDraw)
    }


}