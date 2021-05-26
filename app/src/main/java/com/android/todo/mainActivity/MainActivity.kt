package com.android.todo.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.todo.R
import com.android.todo.base.BaseActivity
import com.android.todo.complete.CompleteActivity
import com.android.todo.database.ToDoTask
import com.android.todo.todoInfoActivity.TodoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : BaseActivity(), RecyclerViewAdapter.ClickListener {

    private val viewModel: MainActivityViewModel by viewModel()

    private var items = mutableListOf<ToDoTask>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            startActivity(Intent(this, TodoActivity::class.java))
        }
        setup()
    }

    override fun onResume() {
        super.onResume()
        setup()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, CompleteActivity::class.java))
        return when (item.itemId) {
            R.id.action_complete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setup() {
        viewModel.getIncompleteTask()
        viewModel.toDoTaskLiveData.observe(this) {
            items = it.toMutableList()
            rv_todo.adapter?.notifyDataSetChanged()

            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val myAdapter = RecyclerViewAdapter(items, this, this)
        rv_todo.layoutManager = LinearLayoutManager(applicationContext)
        items.removeAll { it.status }
        rv_todo.adapter = RecyclerViewAdapter(items, this, this)

        val item = object : SwipeToDeleteCallback( this, 0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(rv_todo)
    }

    override fun onDelete(id: Long) {
        viewModel.deleteItem(id)
    }

}
//        val items = mutableListOf(
//            ToDoTask(1, "wew", "", true),
//            ToDoTask(2, "ew", "", false),
//            ToDoTask(3, "ee", "", true),
//            ToDoTask(4, "rr", "", true),
//            ToDoTask(5, "tt", "", true)
//        )
//
//        var itemWithEe:ToDoTask? = null
//        items.forEach {
//            if (it.title.equals("ee", true)){
//                itemWithEe = it
//                return@forEach
//            }
//        }
//
//        itemWithEe?.let {
//            // witt ee
//
//        }
//
//        if (itemWithEe != null){
//
//        }
//
//        var titleList = mutableListOf<String>()
//
//        items.forEachIndexed { index, toDoTask ->
//            val status = toDoTask.status
//            if (status) {
//                titleList.add(toDoTask.title)
//                println("item2 ${toDoTask.title}")
//            }
//        }
//
//        titleList.forEach {
//            println("Title $it")
//
//        }
//
//        println("items ${items.size}")
//
//        items.forEach {
//            println("taskNumber ${it.TaskNumber}")
//        }



//        val indexList = mutableListOf<Int>()
//
//        items.forEachIndexed { index, toDoTask ->
//            val status = toDoTask.status
//            if (status) {
//                indexList.add(index)
//                println("item2 $toDoTask")
//
//            }
//        }
//
//        indexList.forEach {
//            items.removeAt(it)
//        }
//
//            var number = 12
//
//            number *= 5  //number = number*5
//            println("number  = $number")

//        val number1 =  545344
//        val number2 = number1.toByte()
//        println("number1 = $number1")
//        println("number2 = $number2")
