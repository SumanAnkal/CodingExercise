package com.accenture.codingexercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.codingexercise.R
import com.accenture.codingexercise.adapter.CustomAdapter
import com.accenture.codingexercise.viewModel.MainClassViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main class which launches at the first
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainClassViewModel //Member variable for viewModel
    private lateinit var mAdapter: CustomAdapter //Member variable for Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainClassViewModel::class.java)

        supportActionBar?.title = ""   //Initially ActionBar text will be empty
        swipeRefreshLayout.isRefreshing = true //To Show refresh button the screen

        getDataFromViewModel() //To get data from ViewModel class

        swipeRefreshLayout.setOnRefreshListener {
            getDataFromViewModel()   //To Update new data
        }

        mViewModel.mProgressBar.observe(this, Observer {
            if (it) {
                swipeRefreshLayout.isRefreshing = false
            }
        })

        mViewModel.mResponse.observe(this, Observer {
            supportActionBar?.title =
                it.title  //The title in the ActionBar will updated from the json data.
            mAdapter.setDataToList(it.rows)  //Set aLl data to the list
            swipeRefreshLayout.isRefreshing = false  //Hide the refresh button
        })


        mAdapter = CustomAdapter(this) // Create object of Custom Adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this) //Setting layout manager to recyclerView
        recyclerView.adapter = mAdapter // Setting adapter to recyclerview
    }

    /**
     * Method which gets data from ViewModel
     */
    private fun getDataFromViewModel() {
        mViewModel.getServerResponse()
    }
}
