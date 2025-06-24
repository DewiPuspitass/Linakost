package com.dewipuspitasari0020.linakost.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewipuspitasari0020.linakost.database.PenginapDao
import com.dewipuspitasari0020.linakost.model.Penginap
import com.dewipuspitasari0020.linakost.util.UserDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PenginapViewModel(private val penginapDao: PenginapDao, private val userDataStore: UserDataStore) : ViewModel() {
    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId

    init {
        viewModelScope.launch {
            _userId.value = userDataStore.getUserId().firstOrNull()
        }
    }

    fun addPenginap(
        userId: Int,
        fullName: String,
        numberRoom: String,
        address: String,
        price: Double,
        checkIn: String,
        checkOut: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val currentUserId = _userId.value

            if (currentUserId == null || currentUserId == 0) {
                onError("User ID tidak ditemukan. Mohon login kembali.")
                return@launch
            }

            try {
                val newPenginap = Penginap(
                    userId = currentUserId,
                    fullName = fullName,
                    numberRoom = numberRoom,
                    address = address,
                    price = price,
                    checkIn = checkIn,
                    checkOut = checkOut
                )
                penginapDao.insertPenginap(newPenginap)
                onSuccess()
            } catch (e: Exception) {
                onError("Gagal menambahkan penginap: ${e.localizedMessage ?: "Unknown error"}")
                e.printStackTrace()
            }
        }
    }
}