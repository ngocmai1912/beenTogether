package com.nmai.beentogether.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmai.beentogether.repository.color.ColorUser
import com.nmai.beentogether.repository.lovedate.LoveDate
import com.nmai.beentogether.repository.user.User

class HomeViewModel : ViewModel() {
    private val _loveDate = MutableLiveData<LoveDate>()
    fun setLoveDate(ld: LoveDate){
        _loveDate.postValue(ld)
    }
    val loveDate : LiveData<LoveDate> = _loveDate

    private var _userInfo1 = MutableLiveData<User>()
    fun setUserInfo1(us : User){
        _userInfo1.postValue(us)
    }
    val userInfo1 : LiveData<User> = _userInfo1

    private val _userInfo2 = MutableLiveData<User>()
    fun setUserInfo2(us : User){
        _userInfo2.postValue(us)
    }
    val userInfo2 : LiveData<User> = _userInfo2

    private val _change = MutableLiveData<Int>()
    fun setChange(index : Int){
        _change.postValue(index)
    }
    val change : LiveData<Int> = _change

    private val _color = MutableLiveData<ColorUser>()
    fun setColor(colorUser: ColorUser){
        _color.postValue(colorUser)
    }
    val color : LiveData<ColorUser> = _color
}