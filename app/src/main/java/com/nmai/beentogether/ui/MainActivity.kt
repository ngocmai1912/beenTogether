package com.nmai.beentogether.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.nmai.beentogether.R
import com.nmai.beentogether.repository.lovedate.LoveDateDatabase
import com.nmai.beentogether.repository.user.UserDatabase
import com.nmai.beentogether.ui.home.lovedate.LoveDateFactory
import com.nmai.beentogether.ui.home.lovedate.LoveDateViewModel
import com.nmai.beentogether.ui.home.user.UserFactory
import com.nmai.beentogether.ui.home.user.UserViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}