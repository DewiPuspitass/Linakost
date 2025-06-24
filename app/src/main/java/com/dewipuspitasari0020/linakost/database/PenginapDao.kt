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

    @Query("SELECT * FROM penginaps WHERE id = :penginapId")
    suspend fun getPenginapById(penginapId: Int): Penginap?

     @Update
     suspend fun updatePenginap(penginap: Penginap)

     @Delete
     suspend fun deletePenginap(penginap: Penginap)
}