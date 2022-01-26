package com.project.myapplication.database.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.myapplication.Constants
import com.project.myapplication.database.dao.StockEntityDao
import com.project.myapplication.database.entities.StockEntiity

@Database(version = 4, entities = [StockEntiity::class], exportSchema = false)
abstract class Appdb : RoomDatabase() {


    abstract fun getStockEntityDao(): StockEntityDao


    companion object {

        private var INSTANCE: Appdb? = null

        fun getDatabaseInstance(context: Context): Appdb {

            val tempInstance = INSTANCE
            if (tempInstance == null) {


                synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        Appdb::class.java, Constants.Database_Name
                    )   .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()

                    INSTANCE = instance

                    return instance

                }

            }

           return INSTANCE!!


        }


    }

    //   private var db: Appdb? = null


//    @Synchronized
//    open fun getDb_instance(context: Context): Appdb? {
//        if (db == null) {
//            db = Room.databaseBuilder(
//                context.applicationContext,
//                Appdb::class.java,Constants.Database_Name
//            )
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build()
//        }
//        return db
//    }
//


//    val db = Room.databaseBuilder(
//        applicationContext,
//        Appdb::class.java, "database-name"
//    ).build()

}
