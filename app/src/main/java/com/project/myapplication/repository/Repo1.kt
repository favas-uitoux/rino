package com.project.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.myapplication.database.dao.StockEntityDao
import com.project.myapplication.database.entities.StockEntiity

class Repo1(private val stockEntiityDao: StockEntityDao) {




    fun saveData(tbl:StockEntiity):Long
    {
       return stockEntiityDao.saveDatas(tbl)
    }

    fun updateData(tbl:StockEntiity)
    {
         stockEntiityDao.updateData(tbl)
    }


    fun readSize():LiveData<Int>
    {
        return  stockEntiityDao.getCount()

    }

    fun readData():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readData()
    }

    fun delAll()
    {
        return stockEntiityDao.delAll()
    }

    fun delTxn(id:Int)
    {
         stockEntiityDao.delTxn(id)
    }

    fun readInventory():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readInventory()
    }

    fun readInventoryWithoutDepositDraw():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readInventoryWithoutDepositDraw()
    }


    fun readDeposit():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readDeposit()
    }

    fun readDraw():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readDraw()
    }

    fun readPur():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readPur()
    }

    fun readSale():LiveData<MutableList<StockEntiity>>
    {
        return stockEntiityDao.readSale()
    }

}