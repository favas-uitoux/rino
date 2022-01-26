package com.project.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StockEntiity")
 open class StockEntiity(
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val dat: String,
    val itemName:String,
    val qty:Int,
    val buyPrice:Float,
    val SellingPrice:Float,
    val remarks:String


)