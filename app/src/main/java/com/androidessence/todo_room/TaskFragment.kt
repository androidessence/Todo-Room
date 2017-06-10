package com.androidessence.todo_room

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragment to display a list of tasks.
 */
class TaskFragment : LifecycleFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_task, container, false)

        val recyclerview = view?.findViewById(R.id.task_list) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = layoutManager

        context.taskDao().getAll().observe(this, Observer {
            adapter.tasks = it ?: ArrayList()
        })

        return view
    }
}