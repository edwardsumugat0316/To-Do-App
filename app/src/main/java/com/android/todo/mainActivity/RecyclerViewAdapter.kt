package com.android.todo.mainActivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.green
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.todo.R
import com.android.todo.database.ToDoTask
import com.android.todo.todoInfoActivity.TodoActivity
import kotlinx.android.synthetic.main.todo_view_item.view.*
import java.nio.BufferUnderflowException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter (var items: List<ToDoTask>, private val context:Context, private val clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    interface ClickListener {
        fun onDelete(id: Long)
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.tv_title
        var description: TextView = view.tv_description
        var date: TextView = view.tv_date


        init {
            view.setOnClickListener {
                val position: Int = bindingAdapterPosition
                val id = items[position]
                val taskNumber = id.TaskNumber
                val intent = Intent(context, TodoActivity::class.java).apply {
                    putExtra("id", taskNumber)
                }
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.todo_view_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskItem = items[position]
        holder.itemTitle.text = taskItem.title
        holder.description.text = taskItem.description
        val date = Date(taskItem.createAt)
        val dateFormat = SimpleDateFormat("MM-dd-yyyy\nHH:mm:ss a")
        val currentDate = dateFormat.format(date)

//        val currentDate = DateFormat.getDateTimeInstance().format(date)
        holder.date.text = currentDate

//        holder.deleteBtn.setOnClickListener {
//            val id = taskItem.TaskNumber
//            val mainActivity = context as MainActivity
//            mainActivity.onDelete(id)

//            clickListener.onDelete(id)
//        }

    }

    fun deleteItem(position: Int){
        val taskItem = items[position]
        val id = taskItem.TaskNumber
        clickListener.onDelete(id)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}