package com.example.to_do_list.di

import android.content.Context
import androidx.room.Room
import com.example.to_do_list.data.AppDatabase
import com.example.to_do_list.data.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt dependency injection module for the local database layer.
 *
 * Responsible for providing:
 * - a singleton instance of the Room database
 * - the TodoDao extracted from the database
 *
 * This ensures that the database is created only once
 * and shared across the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides a singleton instance of the Room database.
     *
     * @param context Application context used to create the database
     * @return AppDatabase singleton instance
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()

    /**
     * Provides the TodoDao from the AppDatabase.
     *
     * @param appDatabase The singleton database instance
     * @return TodoDao for database operations on Todo
     * entities
     */
    @Provides
    @Singleton
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao =
        appDatabase.todoDao()
}




