package com.android.todo.complete

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.todo.R
import com.android.todo.base.BaseActivity
import com.android.todo.database.ToDoTask
import com.android.todo.mainActivity.MainActivity
import com.android.todo.mainActivity.MainActivityViewModel
import com.android.todo.mainActivity.RecyclerViewAdapter
import com.android.todo.todoInfoActivity.TodoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.complete.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class CompleteActivity: BaseActivity() , RecyclerViewAdapter.ClickListener {

    private var items  = mutableListOf<ToDoTask>()
    private val viewModel: CompleteActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complete)

        findViewById<FloatingActionButton>(R.id.fab_back).setOnClickListener { view ->
            startActivity(Intent(this, MainActivity::class.java))
        }


        setUp()
    }

    override fun onResume() {
        setUp()
        super.onResume()
    }

    private fun setUp(){
        viewModel.getCompleteTasks()
        viewModel.completeLiveData.observe(this){
            items = it.toMutableList()
            rv_complete.adapter?.notifyDataSetChanged()

            lifecycleScope.launch {
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                }
            }

        }
    }

    private fun setUpRecyclerView() {
        rv_complete.layoutManager = LinearLayoutManager(applicationContext)
        rv_complete.adapter = RecyclerViewAdapter(items, this, this)

    }

    override fun onDelete(id: Long) {
       viewModel.deleteItem(id)
    }
}

