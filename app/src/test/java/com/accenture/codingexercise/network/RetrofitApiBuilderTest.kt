package com.accenture.codingexercise.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.task.accenturelistviewtask.network.IRetrofitApiService
import com.task.accenturelistviewtask.network.RetrofitApiBuilder
import com.task.accenturelistviewtask.network.model.DataModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.CountDownLatch

class RetrofitApiBuilderTest {

    val TAG = "RetrofitApiBuilderTest"
    var mRetrofit: Retrofit? = null
    val mConnectivityManager = Mockito.mock(ConnectivityManager::class.java)
    val mNetworkInfo = Mockito.mock(NetworkInfo::class.java)

    @Mock
    private lateinit var context: Application

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        MockitoAnnotations.initMocks(this)
        mRetrofit = RetrofitApiBuilder.getRetrofitApiBuilder(context!!)
    }

    @Test
    fun getRetrofitApiBuilder() {
        getNetworkConnectivity(true)

        val service = mRetrofit?.create(IRetrofitApiService::class.java)
        val latch = CountDownLatch(1)
        service?.getDataFromServer()?.enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.d(TAG,"Failure")
                latch.countDown()
            }

            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                Log.d(TAG,"Success")
                latch.countDown();
            }
        })
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun getNetworkConnectivity(status: Boolean) {
        Mockito.`when`(context!!.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(mConnectivityManager)
        Mockito.`when`(mConnectivityManager.activeNetworkInfo).thenReturn(mNetworkInfo)
        Mockito.`when`(mNetworkInfo.isConnected()).thenReturn(status)
    }
}