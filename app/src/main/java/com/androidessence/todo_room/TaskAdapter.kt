package com.androidessence.todo_room

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 * Adapter to display a list of tasks.
 */
class TaskAdapter(tasks: List<Task> = ArrayList()) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks: List<Task> = tasks
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context)?.inflate(R.layout.list_item_task, parent, false)
        return TaskViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
        holder?.bindTask(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class TaskViewHolder(view: View?, taskAdapter: TaskAdapter) : RecyclerView.ViewHolder(view) {
        val adapter: WeakReference<TaskAdapter> = WeakReference(taskAdapter)
        val descriptionTextView = view?.findViewById(R.id.task_description) as? TextView
        val completedCheckBox = view?.findViewById(R.id.task_completed) as? CheckBox

        fun bindTask(task: Task) {
            descriptionTextView?.text = task.description
            completedCheckBox?.isChecked = task.completed

            completedCheckBox?.setOnCheckedChangeListener { _, isChecked ->
                adapter.get()?.tasks?.get(adapterPosition)?.completed = isChecked

                //TODO: Update is b0rken
//                Single.fromCallable { itemView.context.taskDao().update(tasks[adapterPosition]) }
//                        .subscribeOn(Schedulers.newThread())
//                        .subscribe()
            }
        }
    }

    companion object {
        private val TAG = TaskAdapter::class.java.simpleName
    }
}