package com.androidessence.todo_room.viewmodel

import android.arch.lifecycle.ViewModel
import com.androidessence.todo_room.data.TaskDAO
import com.androidessence.todo_room.data.AppDatabase
import com.androidessence.todo_room.model.Task
import io.reactivex.Maybe

/**
 * ViewModel for a task
 */
class TaskViewModel: ViewModel() {
    private fun taskDao(): TaskDAO = AppDatabase.getInstance().taskDao()

    fun getTasks(): Maybe<List<Task>> = taskDao().getAll()

    fun insertTasks(tasks: List<Task>): List<Long> = taskDao().insertAll(tasks)
}