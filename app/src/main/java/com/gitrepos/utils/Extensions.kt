package com.gitrepos.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> AppCompatActivity.viewModelProvider(
        provider: ViewModelProvider.Factory) = ViewModelProvider(this, provider)[VM::class.java]