package com.androidessence.todo_room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


/**
 * Base Database class.
 */
@Database(entities = arrayOf(Task::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
            private set

        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        AppDatabase::class.java, "todo-list")
                        .allowMainThreadQueries() //TODO: NO!
                        .build()
            }

            return INSTANCE!!
        }
    }
}