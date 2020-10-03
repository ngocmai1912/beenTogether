package com.nmai.beentogether.repository.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    companion object{
        @Volatile var instance : UserDatabase? = null
        fun getInstance(context: Context) : UserDatabase {
            if(instance == null) instance =
                buidDatabase(context)
            return instance!!
        }

        fun buidDatabase(context: Context) : UserDatabase {
            return Room.databaseBuilder(context, UserDatabase::class.java, "user.db").build()
        }
    }
}