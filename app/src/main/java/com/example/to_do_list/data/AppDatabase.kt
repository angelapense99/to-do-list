package com.example.to_do_list.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database configuration for the application.
 *
 * Defines the database schema and serves as the main access point
 * to persisted data via DAOs.
 *
 * Contains the Todo entity and provides access to TodoDao.
 *
 * Versioning is used to handle schema migrations.
 */

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}