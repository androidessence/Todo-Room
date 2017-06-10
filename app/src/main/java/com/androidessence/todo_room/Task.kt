package com.androidessence.todo_room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Something that has to be completed by the user.
 */
@Entity
class Task {
    @PrimaryKey var id: Int = 0
    var description: String = ""
    var completed: Boolean = false
}