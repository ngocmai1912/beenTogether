package com.nmai.beentogether.repository.lovedate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LoveDate::class), version = 1)
abstract class LoveDateDatabase : RoomDatabase() {
    abstract fun loveDateDao() : LoveDateDao
    companion object{
        @Volatile var instance : LoveDateDatabase? = null
        fun getInstance(context: Context) : LoveDateDatabase {
            if(instance == null) instance =
                buidDatabase(context)
            return instance!!
        }

        fun buidDatabase(context: Context) : LoveDateDatabase {
            return Room.databaseBuilder(context, LoveDateDatabase::class.java, "loveDate.db").build()
        }
    }
}