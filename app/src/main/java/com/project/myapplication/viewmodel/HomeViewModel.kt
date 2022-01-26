package com.project.myapplication.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.myapplication.Constants
import com.project.myapplication.database.appdb.Appdb
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.interfac.HomeActivityInterface
import com.project.myapplication.repository.Repo1


class HomeViewModel(application: Application):AndroidViewModel(application),HomeActivityInterface {


    private val repo:Repo1

    init {
        val stockEntityDao= Appdb.getDatabaseInstance(application).getStockEntityDao()
        repo= Repo1(stockEntityDao)
        Constants.homeActivityInterface=this

    }

//    fun readSize(): LiveData<Int>
//    {
//      return  repo.readSize()
//    }

    fun saveData(tbl:StockEntiity):Long
    {
    return    repo.saveData(tbl)
    }


    fun readData():LiveData<MutableList<StockEntiity>>
    {
        return repo.readData()
    }



    fun delAll()
    {
        return repo.delAll()
    }


    override fun delete_txn(id: Int) {
        repo.delTxn(id)
    }

    override fun updateDtata(tbl: StockEntiity) {

        repo.updateData(tbl)
    }


}