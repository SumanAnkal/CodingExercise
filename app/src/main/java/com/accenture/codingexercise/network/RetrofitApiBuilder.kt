package com.task.accenturelistviewtask.network

import android.content.Context
import com.accenture.codingexercise.appConstant.Constant.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient


object RetrofitApiBuilder {
    private var retrofit: Retrofit? = null

    fun getRetrofitApiBuilder(mContext: Context): Retrofit? {
        if (retrofit == null) {

            val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(mContext))


            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient.build())
                .build()

        }
        return retrofit
    }
}