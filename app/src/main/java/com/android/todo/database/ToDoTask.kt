package com.android.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoTask")
data class ToDoTask (
        @PrimaryKey(autoGenerate = true)val TaskNumber: Long = 0L,
            val title: String,
            val description: String,
            val status: Boolean,
            val createAt: Long
        )

