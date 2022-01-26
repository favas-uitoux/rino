package com.project.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.project.myapplication.database.entities.StockEntiity

@Dao
interface StockEntityDao {





    @Query("Select * from stockentiity order by id asc")
    fun readData(): LiveData<MutableList<StockEntiity>>


    @Query("Select count (*) from stockentiity ")
   fun getCount(): LiveData<Int>

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun saveDatas(tbl:StockEntiity):Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateData(tbl:StockEntiity)


   @Query("Delete  from stockentiity ")
    fun delAll()


    @Query("Delete  from stockentiity where id=:id")
    fun delTxn(id:Int)

    @Query("Select * from stockentiity order by itemName asc")
    fun readInventory(): LiveData<MutableList<StockEntiity>>


    @Query("Select * from stockentiity where itemName !='Deposit' and itemName !='Draw' order by itemName asc")
    fun readInventoryWithoutDepositDraw(): LiveData<MutableList<StockEntiity>>

    @Query("Select * from stockentiity where itemName = 'Deposit'   order by itemName asc")
    fun readDeposit(): LiveData<MutableList<StockEntiity>>

    @Query("Select * from stockentiity where itemName ='Draw'  order by itemName asc")
    fun readDraw(): LiveData<MutableList<StockEntiity>>

    @Query("Select * from stockentiity where itemName !='Deposit' and itemName !='Draw' and buyPrice>0 order by itemName asc")
    fun readPur(): LiveData<MutableList<StockEntiity>>

    @Query("Select * from stockentiity where itemName !='Deposit' and itemName !='Draw' and SellingPrice>0 order by itemName asc")
    fun readSale(): LiveData<MutableList<StockEntiity>>
}