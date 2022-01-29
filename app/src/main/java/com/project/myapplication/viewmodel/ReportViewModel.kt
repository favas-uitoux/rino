package com.project.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.myapplication.Constants
import com.project.myapplication.database.appdb.Appdb
import com.project.myapplication.database.entities.StockEntiity
import com.project.myapplication.repository.Repo1

class ReportViewModel(application: Application) :AndroidViewModel(application) {
    private val repo: Repo1

    init {
        val stockEntityDao= Appdb.getDatabaseInstance(application).getStockEntityDao()
        repo= Repo1(stockEntityDao)

    }

    fun readInventory(): LiveData<MutableList<StockEntiity>>
    {
        return repo.readInventory()
    }



    fun  readInventoryWithoutDepositDraw(): LiveData<MutableList<StockEntiity>>
    {
        return repo. readInventoryWithoutDepositDraw()
    }


    fun  readDeposit(): LiveData<MutableList<StockEntiity>>
    {
        return repo. readDeposit()
    }

    fun  readDraw(): LiveData<MutableList<StockEntiity>>
    {
        return repo. readDraw()
    }

    fun  readPur(): LiveData<MutableList<StockEntiity>>
    {
        return repo. readPur()
    }

    fun  readSale(): LiveData<MutableList<StockEntiity>>
    {
        return repo. readSale()
    }

    fun  readToatalDeposit(): Float
    {
        return repo.readToatalDeposit()
    }

    fun  readTotalDraw(): Float
    {
        return repo.readTotalDraw()
    }

    fun  readSer(ser:String): MutableList<StockEntiity>
    {
        return repo.readSer(ser)
    }

}