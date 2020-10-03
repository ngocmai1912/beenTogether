package com.nmai.beentogether.ui.home.lovedate

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nmai.beentogether.repository.lovedate.LoveDateDatabase

class LoveDateFactory(val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val loveDateDao = LoveDateDatabase.getInstance(context).loveDateDao()
        return LoveDateViewModel(loveDateDao) as T
    }
}