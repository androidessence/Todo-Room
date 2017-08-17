package com.androidessence.todo_room

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase


/**
 * Base Database class.
 */
@Database(entities = arrayOf(Task::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
            private set

        fun getInstance(): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(App.instance,
                        AppDatabase::class.java, "todo-list")
                        .build()
            }

            return INSTANCE!!
        }
    }
}