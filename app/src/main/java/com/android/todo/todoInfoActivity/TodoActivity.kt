package com.android.todo.todoInfoActivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import com.android.todo.R
import com.android.todo.base.BaseActivity
import com.android.todo.complete.CompleteActivity
import com.android.todo.mainActivity.MainActivity
import kotlinx.android.synthetic.main.add_todo_activity.*
import kotlinx.android.synthetic.main.todo_view_item.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class TodoActivity : BaseActivity() {

    private val viewModel: TodoActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo_activity)

        val id = intent.getLongExtra("id", 0)
        setup(id)

        back_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        if (id != 0L) {
            val task = viewModel.getTask(id)
            setText(task.title, task.description, task.status)
        }

    }
    private fun setup(taskId: Long) {
        button_save.setOnClickListener {
            val title = et_title.text.toString()
            val description = et_description.text.toString()
            val createdAt = Date().time

            if (title.isNotEmpty() && description.isNotEmpty()) {
                if (taskId == 0L) {
                    viewModel.saveTask(title, description, switch_status.isChecked, createdAt)
                } else {
                    viewModel.updateTask(taskId, title, description, switch_status.isChecked, createdAt)
                }
                finish()
                println("title $title description $description")
            }
        }
    }


    private fun setText(title: String, description: String, status: Boolean) {
        et_title.setText(title)
        et_description.setText(description)
        switch_status.isChecked = status
    }
}