package com.android.todo.repository

import com.android.todo.database.AppDataBase
import com.android.todo.database.ToDoTask
import kotlinx.coroutines.flow.Flow

class RepositoryImp(private val dataBase: AppDataBase): Repository {
    override suspend fun insertTask(tasks: ToDoTask) {
            dataBase.toDoTaskDao().insertTask(tasks)
        }

    override suspend fun saveTask(task: List<ToDoTask>) {
        task.forEach {
            dataBase.toDoTaskDao().insertTask(it)
        }

    }

    override fun update(tasks: ToDoTask) {
        dataBase.toDoTaskDao().update(tasks)
    }

    override fun delete(taskNumber: Long) {
        dataBase.toDoTaskDao().delete(taskNumber)
    }



    override fun getTask(taskNumber: Long): ToDoTask {
       return dataBase.toDoTaskDao().getTaskId(taskNumber)
    }


    override fun getAllTasksByStatus(status: Boolean): List<ToDoTask> {
        return dataBase.toDoTaskDao().getTasksByStatus(status)
    }


    override fun searchTask(search: String, status: Boolean): List<ToDoTask> {
        return dataBase.toDoTaskDao().searchTask(search, status)
    }
}