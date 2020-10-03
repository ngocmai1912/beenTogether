package com.nmai.beentogether.ui.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.repository.user.UserDao

class UserViewModel(val userDao: UserDao) : ViewModel() {

    fun getUser1() : LiveData<User?>{
        return userDao.findUser(1)
    }
    fun getUser2() : LiveData<User?>{
        return userDao.findUser(2)
    }
    fun insert(user: User){
        userDao.insertUser(user)
    }
    fun update(user: User){
        userDao.updateUser(user)
    }
}