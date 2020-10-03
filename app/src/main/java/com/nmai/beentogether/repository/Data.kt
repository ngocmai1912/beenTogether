package com.nmai.beentogether.repository

import android.R.attr
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import androidx.annotation.RequiresApi
import com.nmai.beentogether.R
import com.nmai.beentogether.repository.color.ColorUser
import com.nmai.beentogether.repository.lovedate.LoveDate
import com.nmai.beentogether.repository.quote.Quote
import com.nmai.beentogether.repository.user.User
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


object Data {
    var userInfo1 : User? = null
    var userInfo2 : User? = null
    var loveDate = mutableListOf<LoveDate>()

    fun bitmapToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return encoded
    }
    fun stringToBitmap(bitmapBase64 : String): Bitmap {
        val decodedString: ByteArray = android.util.Base64.decode(bitmapBase64, android.util.Base64.DEFAULT)
        val decodedByte: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return decodedByte
    }

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    fun changeDateToString(date: Date) : String{
        return simpleDateFormat.format(date)
    }
    fun changeStringToDate(string: String) : Date{
        return simpleDateFormat.parse(string)
    }
    val listColor = listOf<ColorUser>(
        ColorUser(R.color.colorUser1),
        ColorUser(R.color.colorUser2),
        ColorUser(R.color.colorUser3),
        ColorUser(R.color.colorUser4),
        ColorUser(R.color.colorUser5),
        ColorUser(R.color.colorUser6),
        ColorUser(R.color.colorUser7),
        ColorUser(R.color.colorUser8),
        ColorUser(R.color.colorUser9),
        ColorUser(R.color.colorUser10),
        ColorUser(R.color.colorUser11),
        ColorUser(R.color.colorUser12)
    )

    val listQuote = listOf<Quote>(
        Quote(
            "I automatically smile when you message me."
        ),
        Quote(
            "I love you, and that's the beginning and the end of everything."
        ),
        Quote(
            "Fall in love with someone who doesn't make you think love is hard."
        ),
        Quote(
            "You make me happy in a way no one else can."
        ),
        Quote(
            "Love is not what you say. Love is what you do."
        ),
        Quote(
            "Not all storms come to disrupt your life. Some come to clear your path."
        ),
        Quote(
            "How you love yourself is how you teach others to love you."
        ),
        Quote(
            "And in the middle of my chaos, there was you."
        ),
        Quote(
            "Sometimes, home can be another person."
        ),
        Quote(
            "Love is difficult, but all the best things are."
        ),
        Quote(
            "You walk in, and my heart beats different."
        ),
        Quote(
            "Listen to the silence. It's telling the truth."
        ),
        Quote(
            "A memory is a start, or a stain."
        ),
        Quote(
            "The moon and the sun are love and fear. One fades as other rises."
        ),
        Quote(
            "The only one who always stays is yourself."
        ),
        Quote(
            "Distance gives us reason to love harder."
        ),
        Quote(
            "You will forever be my always."
        ),
        Quote(
            "You are my sun, my moon and all of my stars."
        ),
        Quote(
            "Love is patient. Love is kind."
        )
    )
}