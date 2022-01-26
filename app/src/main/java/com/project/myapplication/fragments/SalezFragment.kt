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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.myapplication.R
import com.project.myapplication.adapter.AdapterSalez

import com.project.myapplication.viewmodel.HomeViewModel

class SalezFragment : Fragment() {
//    private var _binding: FragmentSalesNewBinding? = null
//    private val binding get() = _binding!!

    private lateinit var view1 : View
    private lateinit var viewModel: HomeViewModel
    private lateinit var adp:AdapterSalez
    private lateinit var recv1:RecyclerView
    private lateinit var fab1:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  _binding = FragmentSalesNewBinding.inflate(inflater, container, false)
     //   val view1 = binding.root
         view1= inflater.inflate(R.layout.fragment_sales_new,container,false)


        init()
        display()
        fab1.setOnClickListener {
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(R.id.frame1, DoSaleFragment(), "DoSaleFragment").commit()

            viewModel.delAll()

        }
        return view1



    }

    private fun  init()
    {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        recv1=view1.findViewById(R.id.recv1)
        fab1=view1.findViewById(R.id.fab1)
    }



    private fun display() {

        viewModel.readData().observe(viewLifecycleOwner, Observer {


            adp = AdapterSalez(view1.context, it)
            recv1.layoutManager = LinearLayoutManager(view1.context)
            recv1.adapter = adp
        })


    }


}