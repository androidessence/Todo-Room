package com.androidessence.todo_room.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.androidessence.todo_room.R
import com.androidessence.todo_room.data.AppDatabase
import com.androidessence.todo_room.model.Task
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    override fun getItemCount(): Int = tasks.size

    class TaskViewHolder(view: View?, taskAdapter: TaskAdapter) : RecyclerView.ViewHolder(view) {
        private val adapter: WeakReference<TaskAdapter> = WeakReference(taskAdapter)
        private val descriptionTextView = view?.findViewById(R.id.task_description) as? TextView
        private val completedCheckBox = view?.findViewById(R.id.task_completed) as? CheckBox

        // https://stackoverflow.com/questions/44477568/calling-an-rxjava-single-in-kotlin-lambda
        private lateinit var emitter: ObservableEmitter<Task>
        private val disposable: Disposable = Observable.create(ObservableOnSubscribe<Task> { e -> emitter = e })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe({ AppDatabase.getInstance().taskDao().update(it) })

        fun bindTask(task: Task) {
            descriptionTextView?.text = task.description
            completedCheckBox?.isChecked = task.completed

            completedCheckBox?.setOnCheckedChangeListener { _, isChecked ->
                adapter.get()?.tasks?.get(adapterPosition)?.completed = isChecked
                emitter.onNext(adapter.get()?.tasks?.get(adapterPosition))
            }
        }
    }
}