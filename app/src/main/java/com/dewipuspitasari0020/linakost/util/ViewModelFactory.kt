package com.dewipuspitasari0020.linakost.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dewipuspitasari0020.linakost.database.AppDatabase
import com.dewipuspitasari0020.linakost.viewModel.LoginViewModel
import com.dewipuspitasari0020.linakost.viewModel.PenginapViewModel
import com.dewipuspitasari0020.linakost.viewModel.RegisterViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    private val userDao by lazy { AppDatabase.getDatabase(context).userDao() }
    val userDataStore = UserDataStore(context)
    private val penginapDao by lazy { AppDatabase.getDatabase(context).penginapDao() }

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userDao) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userDao, userDataStore) as T
        } else if (modelClass.isAssignableFrom(PenginapViewModel::class.java)) {
            return PenginapViewModel(penginapDao, userDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}