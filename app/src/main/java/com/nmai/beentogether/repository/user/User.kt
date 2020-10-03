package com.nmai.beentogether.repository.user

import android.graphics.Bitmap
import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey val _id : Int,
    @ColumnInfo(name = "_nickName") var nickName : String,
    @ColumnInfo(name = "_avatar") var avatar : String,
    @ColumnInfo(name = "_birthday") var birthday : String?,
    @ColumnInfo(name = "_gender") var gender : String,
    @ColumnInfo(name = "_borderColorCode") var borderColorCode : Int
){
}