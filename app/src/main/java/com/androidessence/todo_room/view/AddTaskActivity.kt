package com.androidessence.todo_room.view

import android.os.Bundle
import android.widget.TextView
import com.androidessence.todo_room.R
import com.androidessence.todo_room.model.Task
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddTaskActivity : CoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val editText = findViewById(R.id.task_description) as TextView
        val submit = findViewById(R.id.submit)

        submit.setOnClickListener {
            val description = editText.text.toString()

            if (!description.isEmpty()) {
                val task = Task(description)

                Single.fromCallable { taskViewModel.insertTasks(listOf(task)) }
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()

                finish()
            }
        }
    }
}
