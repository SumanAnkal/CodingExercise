package com.task.accenturelistviewtask.network

import com.accenture.codingexercise.appConstant.Constant.GET_FACTS
import com.task.accenturelistviewtask.network.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface IRetrofitApiService {

    @GET(GET_FACTS)
    fun getDataFromServer(): Call<DataModel>
}