package com.fingerth.demo.logic

import androidx.lifecycle.liveData
import com.fingerth.sunnyweather.logic.network.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {
    fun getJokePics(map: Map<String, String>) = resp(Dispatchers.IO) {
        val placeResponse = NetworkUtils.getJokePics(map)
        if (placeResponse.status.code == 200) {
            Result.success(placeResponse)
        } else {
            Result.failure(RuntimeException("response status is null"))
        }
    }

    private fun <T> resp(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}