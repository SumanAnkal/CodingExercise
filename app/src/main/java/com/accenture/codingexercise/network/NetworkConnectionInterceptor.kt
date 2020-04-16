package com.task.accenturelistviewtask.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {

    val isConnected: Boolean
        get() {
            val connectivityManager =
                mContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw InternetConnectiveException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}