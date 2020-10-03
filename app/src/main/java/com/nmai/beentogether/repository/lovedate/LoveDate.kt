package com.nmai.beentogether.repository.lovedate

import android.graphics.Bitmap
import android.media.Image
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LoveDate(
    @PrimaryKey val _id : Int,
    @ColumnInfo(name = "_startDate") var startDate : String,
    @ColumnInfo(name = "_topTitle") var topTitle : String,
    @ColumnInfo(name = "_bottomTitle") var bottomTitle : String,
    @ColumnInfo(name = "_wallpaper") var wallpaper : String,
    @ColumnInfo(name = "_loveColor") var loveColor : Int
)