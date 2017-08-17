package com.androidessence.todo_room.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.androidessence.todo_room.R
import com.androidessence.todo_room.model.Task
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TaskActivity : CoreActivity() {
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val recyclerView = findViewById(R.id.task_list) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    override fun bind() {
        super.bind()

        subscription.add(taskViewModel.getTasks()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.displayTasks(it) }))
    }

    private fun displayTasks(tasks: List<Task>) {
        adapter.tasks = tasks
    }
}
