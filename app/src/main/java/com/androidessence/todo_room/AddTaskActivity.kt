package com.androidessence.todo_room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.androidessence.todo_room.viewmodel.TaskViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddTaskActivity : AppCompatActivity() {
    private val taskViewModel = TaskViewModel()

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
