package com.dewipuspitasari0020.linakost.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dewipuspitasari0020.linakost.database.AppDatabase
import com.dewipuspitasari0020.linakost.viewModel.RegisterViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    private val userDao by lazy { AppDatabase.getDatabase(context).userDao() }

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}