package com.android.todo.complete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo.database.ToDoTask
import com.android.todo.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompleteActivityViewModel(private val repository: Repository): ViewModel() {
    val completeLiveData: MutableLiveData<List<ToDoTask>> = MutableLiveData()

    fun deleteItem(item: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.delete(item)
                getCompleteTasks()
            }
        }
    }

    fun getCompleteTasks(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
               val completeList =  repository.getAllTasksByStatus(true)
                withContext(Dispatchers.Main){
                    completeLiveData.value = completeList
                }
            }
        }
    }
}