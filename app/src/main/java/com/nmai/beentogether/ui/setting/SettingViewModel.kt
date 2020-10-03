package com.nmai.beentogether.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    private val _shareSwitchNotification = MutableLiveData<Int>()
    fun setShareNotification(x : Int){
        _shareSwitchNotification.postValue(x)
    }
    val shareSwitchNotification : LiveData<Int> = _shareSwitchNotification

    private val _shareSwitchQuote = MutableLiveData<Int>()
    fun setShareQuote(x : Int){
        _shareSwitchNotification.postValue(x)
    }
    val shareSwitchQuote : LiveData<Int> = _shareSwitchQuote
}