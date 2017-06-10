package com.androidessence.todo_room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Interface for retrieving Task info.
 */
@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE completed = :arg0")
    fun getComplete(complete: Boolean): List<Task>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}