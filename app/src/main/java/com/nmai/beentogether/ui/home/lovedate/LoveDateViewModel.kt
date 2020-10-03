package com.nmai.beentogether.ui.home.lovedate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmai.beentogether.repository.lovedate.LoveDate
import com.nmai.beentogether.repository.lovedate.LoveDateDao

class LoveDateViewModel(val loveDateDao: LoveDateDao) : ViewModel(){
    fun getData() : LiveData<LoveDate?>{
        return loveDateDao.getLoveDate()
    }
    fun insert(loveDate: LoveDate){
        loveDateDao.insertLoveDate(loveDate)
    }
    fun update(loveDate: LoveDate){
        loveDateDao.updateLoveDate(loveDate)
    }
}