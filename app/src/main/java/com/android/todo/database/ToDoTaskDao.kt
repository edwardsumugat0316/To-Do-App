package com.android.todo.database

import androidx.room.*

@Dao
interface ToDoTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: ToDoTask)

    @Query(" DELETE FROM ToDoTask WHERE TaskNumber = :taskNumber")
    fun delete(taskNumber: Long)

    @Update
    fun update(toDoTask: ToDoTask)

    @Query("SELECT * FROM ToDoTask WHERE status = 0")
    fun getIncompleteTasks():List<ToDoTask>


    @Query("SELECT * FROM ToDoTask WHERE status =:status")
    fun getTasksByStatus(status: Boolean):List<ToDoTask>


    @Query("SELECT * FROM ToDoTask")
    fun getAll():List<ToDoTask>

    @Query("SELECT * FROM ToDoTask WHERE TaskNumber = :taskNumber")
    fun getTaskId(taskNumber: Long): ToDoTask

    @Query( "SELECT * FROM ToDoTask WHERE status = :status")
    fun getStatus(status: Boolean): ToDoTask


}