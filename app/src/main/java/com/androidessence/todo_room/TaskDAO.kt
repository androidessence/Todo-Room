package com.androidessence.todo_room

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Interface for retrieving Task info.
 */
@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")
    fun getAll(): Maybe<List<Task>>

    @Query("SELECT * FROM task WHERE completed = :arg0")
    fun getTasksByCompletion(complete: Boolean): Flowable<List<Task>>

    @Insert
    fun insertAll(tasks: List<Task>): List<Long>

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}