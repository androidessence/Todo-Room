package com.androidessence.todo_room

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val recyclerview = findViewById(R.id.task_list) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = layoutManager

        taskDao().getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ adapter.tasks = it })

        val fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}
