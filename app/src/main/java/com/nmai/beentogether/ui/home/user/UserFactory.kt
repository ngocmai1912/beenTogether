package com.nmai.beentogether.ui.home.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.repository.user.UserDatabase

class UserFactory(val context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userDao = UserDatabase.getInstance(context).userDao()
        return UserViewModel(userDao) as T
    }
}