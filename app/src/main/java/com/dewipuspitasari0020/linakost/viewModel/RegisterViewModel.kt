package com.dewipuspitasari0020.linakost.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dewipuspitasari0020.linakost.database.UserDao
import com.dewipuspitasari0020.linakost.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val dao: UserDao) : ViewModel() {

    fun registerUser(
        fullName: String,
        email: String,
        passwordPlain: String,
        longitude: Double,
        latitude: Double,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val passwordHash = passwordPlain

                val newUser = User(
                    fullName = fullName,
                    email = email,
                    password = passwordHash,
                    longitude = longitude,
                    latitude = latitude
                )

                val existingUser = dao.getUserByEmail(newUser.email)
                if (existingUser != null) {
                    onError("Email sudah terdaftar.")
                    return@launch
                }

                dao.insertUser(newUser)
                onSuccess()
            } catch (e: Exception) {
                onError("Gagal mendaftar: ${e.localizedMessage ?: "Unknown error"}")
                e.printStackTrace()
            }
        }
    }
}

class RegisterViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}