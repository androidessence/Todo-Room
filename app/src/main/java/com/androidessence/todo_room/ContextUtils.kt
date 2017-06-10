package com.androidessence.todo_room

import android.content.Context

fun Context.taskDao(): TaskDAO {
    return AppDatabase.getInMemoryDatabase(this).taskDao()
}