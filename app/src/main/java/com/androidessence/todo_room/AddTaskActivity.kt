package com.androidessence.todo_room

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val editText = findViewById(R.id.task_description) as TextView
        val submit = findViewById(R.id.submit) as Button

        submit.setOnClickListener {
            if (!editText.text.toString().isEmpty()) {
                val task = Task()
                task.description = editText.text.toString()

                AppDatabase.getInMemoryDatabase(this).taskDao().insertAll(task)
                finish()
            }
        }
    }
}
