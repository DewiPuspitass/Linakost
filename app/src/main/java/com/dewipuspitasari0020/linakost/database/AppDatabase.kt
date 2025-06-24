package com.dewipuspitasari0020.linakost.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dewipuspitasari0020.linakost.model.Penginap
import com.dewipuspitasari0020.linakost.model.User

@Database(entities = [User::class, Penginap::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun penginapDao(): PenginapDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "linakost"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}