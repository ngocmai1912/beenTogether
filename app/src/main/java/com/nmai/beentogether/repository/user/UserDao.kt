package com.nmai.beentogether.repository.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE _id LIKE :id")
    fun findUser(id : Int) : LiveData<User?>
    @Update
    fun updateUser(user: User)
    @Insert
    fun insertUser(user: User)
}