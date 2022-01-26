package com.project.myapplication.interfac

import com.project.myapplication.database.entities.StockEntiity

interface HomeActivityInterface {

    fun delete_txn(id:Int)
    fun updateDtata(tbl:StockEntiity)

}