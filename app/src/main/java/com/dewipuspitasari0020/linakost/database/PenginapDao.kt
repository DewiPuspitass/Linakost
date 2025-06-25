package com.dewipuspitasari0020.linakost.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dewipuspitasari0020.linakost.model.Penginap
import kotlinx.coroutines.flow.Flow

@Dao
interface PenginapDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPenginap(penginap: Penginap)

    @Query("SELECT * FROM penginaps WHERE userId = :userId ORDER BY checkIn DESC")
    fun getPenginapsByUserId(userId: Int): Flow<List<Penginap>>

    @Query("SELECT * FROM penginaps WHERE id = :id")
    suspend fun getBarangById(id: Int): Penginap?

     @Update
     suspend fun updatePenginap(penginap: Penginap)

    @Query("DELETE FROM penginaps WHERE id = :id")
    suspend fun deleteById(id: Int)
}