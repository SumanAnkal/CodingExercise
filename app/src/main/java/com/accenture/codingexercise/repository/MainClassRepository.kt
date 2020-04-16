package com.accenture.codingexercise.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.task.accenturelistviewtask.network.IRetrofitApiService
import com.task.accenturelistviewtask.network.InternetConnectiveException
import com.task.accenturelistviewtask.network.RetrofitApiBuilder
import com.task.accenturelistviewtask.network.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository to perform Network Operations
 */
class MainClassRepository (val application: Application) {

    val mResponse = MutableLiveData<DataModel>()
    val mProgressBar = MutableLiveData<Boolean>()

    fun callServerForData(){

        val retrofit = RetrofitApiBuilder.getRetrofitApiBuilder(application)
        val retrofitService = retrofit?.create(IRetrofitApiService::class.java)

        retrofitService?.getDataFromServer()?.enqueue(object : Callback<DataModel>{

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                if(t is InternetConnectiveException){
                    mProgressBar.value = true
                    Toast.makeText(application, "No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                mResponse.value = response.body()
            }
        })
    }
}