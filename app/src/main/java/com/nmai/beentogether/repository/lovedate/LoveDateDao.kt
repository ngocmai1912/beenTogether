package com.nmai.beentogether.repository.lovedate

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nmai.beentogether.repository.user.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LoveDateDao {
    @Query("SELECT * FROM LoveDate")
    fun getLoveDate() : LiveData<LoveDate?>
    @Update
    fun updateLoveDate(loveDate: LoveDate)
    @Insert
    fun insertLoveDate(loveDate: LoveDate)
}