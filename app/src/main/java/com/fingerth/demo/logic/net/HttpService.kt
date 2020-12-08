package com.fingerth.demo.logic.net

import com.fingerth.demo.logic.model.JokePicBean
import retrofit2.Call
import retrofit2.http.*

interface HttpService {
    @GET("joke/")
    fun getJokePics(@QueryMap map: Map<String, String>): Call<JokePicBean>

    @FormUrlEncoded
    @POST("loginV2/m_confirm")
    fun onLogin(@FieldMap map: Map<String, String>): Call<String>
}
