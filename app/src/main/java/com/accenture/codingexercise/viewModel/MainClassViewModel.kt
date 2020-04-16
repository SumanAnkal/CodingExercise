package com.accenture.codingexercise.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.accenture.codingexercise.repository.MainClassRepository
import com.task.accenturelistviewtask.network.model.DataModel

/**
 * ViewModel class with is bridge between Repository and UI
 */
class MainClassViewModel (application: Application) : AndroidViewModel(application) {

    private val mRepository = MainClassRepository(application)
    val mResponse: LiveData<DataModel>
    val mProgressBar: LiveData<Boolean>

    init {
        this.mResponse = mRepository.mResponse
        this.mProgressBar = mRepository.mProgressBar
    }

    /**
     * Method to get data from Repository
     */
    fun getServerResponse() {
        mRepository.callServerForData()
    }
}