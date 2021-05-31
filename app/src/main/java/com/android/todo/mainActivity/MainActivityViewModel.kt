package com.android.todo.mainActivity

import android.icu.text.CaseMap
import androidx.lifecycle.*
import com.android.todo.database.ToDoTask
import com.android.todo.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val repository: Repository): ViewModel() {
    val toDoTaskLiveData: MutableLiveData<List<ToDoTask>> = MutableLiveData()

    

    fun saveTask(task: List<ToDoTask>){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.saveTask(task)
            }
        }
    }

    fun deleteItem(item: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.delete(item)
                getIncompleteTask()
            }
        }
    }

    fun updateTask(task: ToDoTask){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
             repository.update(task)
            }
        }
    }

    fun getIncompleteTask(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val itemList = repository.getAllTasksByStatus(false)
                withContext(Dispatchers.Main){
                    toDoTaskLiveData.value = itemList
                }
            }
        }
    }

    fun onSearch(search: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val itemSearch = repository.searchTask(search,false)
                withContext(Dispatchers.Main){
                    toDoTaskLiveData.value = itemSearch
                }
            }
        }
    }

}