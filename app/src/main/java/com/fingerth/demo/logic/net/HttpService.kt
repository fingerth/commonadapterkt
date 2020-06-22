package com.fingerth.sunnyweather.logic.network

import com.fingerth.demo.logic.model.JokePicBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HttpService {
    @GET("joke/")
    fun getJokePics(@QueryMap map: Map<String, String>): Call<JokePicBean>
}