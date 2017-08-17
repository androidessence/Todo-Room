package com.androidessence.todo_room.view

import android.support.v7.app.AppCompatActivity
import com.androidessence.todo_room.viewmodel.TaskViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base Activity with subscription code.
 */
open class CoreActivity : AppCompatActivity() {
    protected lateinit var subscription: CompositeDisposable
    protected val taskViewModel = TaskViewModel()

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unBind()
    }

    open protected fun bind() {
        subscription = CompositeDisposable()
    }

    open protected fun unBind() {
        subscription.dispose()
    }
}