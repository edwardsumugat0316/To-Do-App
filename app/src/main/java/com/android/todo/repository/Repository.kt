package com.android.todo.repository

import com.android.todo.database.ToDoTask

interface Repository {
    suspend fun insertTask(tasks:ToDoTask )

    fun delete(taskNumber: Long)

    fun update(tasks: ToDoTask)

    suspend fun saveTask(task: List<ToDoTask>)

    fun getIncompleteTasks(): List<ToDoTask>

    fun getTask(taskNumber: Long): ToDoTask

    fun getStatus(status: Boolean): ToDoTask

    fun getAllCompleteTasks(status: Boolean): List<ToDoTask>
}