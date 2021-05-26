package com.android.todo.todoInfoActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo.database.ToDoTask
import com.android.todo.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoActivityViewModel(private val repository: Repository): ViewModel() {
    val toDoTaskLiveData: MutableLiveData<ToDoTask> = MutableLiveData()

    fun saveTask(title: String, description: String, status: Boolean, createdAt: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertTask(ToDoTask( title = title, description = description,status = status, createAt = createdAt ))
            }
        }
    }

    fun updateTask(taskId: Long, title: String, description: String, status: Boolean, createdAt: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.update(ToDoTask(TaskNumber = taskId, title = title, description = description,status = status, createAt = createdAt ))
            }
        }
    }

    fun getTask(taskId: Long): ToDoTask{
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getTask(taskId)
            }
        }
        return repository.getTask(taskId)
    }
}