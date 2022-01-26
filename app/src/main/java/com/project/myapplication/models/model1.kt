package com.project.myapplication.models

import com.project.myapplication.database.entities.StockEntiity

class model1(

    val id1: Int,
    val dat1: String,
    val itemName1: String,
    val qty1: Int,
    val buyPrice1: Float,
    val SellingPrice1: Float,
    val tt:Float,
    var flag1:Boolean

    ) :
    StockEntiity(
    id1,
    dat1,
    itemName1,
    qty1,
    buyPrice1,
    SellingPrice1,
        ""

) {


}