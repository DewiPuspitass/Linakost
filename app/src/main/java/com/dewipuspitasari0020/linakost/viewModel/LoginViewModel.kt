package com.dewipuspitasari0020.linakost.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewipuspitasari0020.linakost.database.UserDao
import com.dewipuspitasari0020.linakost.model.User
import com.dewipuspitasari0020.linakost.util.UserDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDao, private val userDataStore: UserDataStore): ViewModel() {
    private val _loginStatus = MutableStateFlow<AuthStatus>(AuthStatus.Idle)
    val loginStatus: StateFlow<AuthStatus> = _loginStatus

    fun login(email: String, password: String){
        _loginStatus.value = AuthStatus.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = dao.login(email, password)

                if (user != null) {
                    userDataStore.saveUserData(user.id.toString(), user.email)
                    _loginStatus.value = AuthStatus.LoginSuccess(user)
                    Log.d("UserViewModel", "Login successful for: ${user.email}")
                } else {
                    _loginStatus.value = AuthStatus.Error("Email atau password salah.")
                    Log.w("UserViewModel", "Login failed: Incorrect credentials for $email")
                }
            } catch (e: Exception) {
                _loginStatus.value = AuthStatus.Error("Terjadi kesalahan: ${e.message ?: "Unknown error"}")
                Log.e("UserViewModel", "Login error: ${e.message}", e)
            }
        }
    }

    fun resetLoginStatus() {
        _loginStatus.value = AuthStatus.Idle
    }
}

sealed class AuthStatus {
    object Idle : AuthStatus()
    object Loading : AuthStatus()
    object Success : AuthStatus()
    data class Error(val message: String) : AuthStatus()
    data class LoginSuccess(val user: User) : AuthStatus()
}