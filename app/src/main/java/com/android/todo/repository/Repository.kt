package com.android.todo.repository

import com.android.todo.database.ToDoTask
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertTask(tasks: ToDoTask)

    fun delete(taskNumber: Long)

    fun update(tasks: ToDoTask)

    suspend fun saveTask(task: List<ToDoTask>)

    fun getTask(taskNumber: Long): ToDoTask

    fun getAllTasksByStatus(status: Boolean): List<ToDoTask>

    fun searchTask(search: String, status: Boolean): List<ToDoTask>
}