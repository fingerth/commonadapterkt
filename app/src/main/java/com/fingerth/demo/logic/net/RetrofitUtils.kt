package com.fingerth.sunnyweather.logic.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

object RetrofitUtils {
    private const val DEFAULT_TIMEOUT = 120L
    private const val TAG = "RetrofitUtils"
    private const val BASE_URL = "https://bird.ioliu.cn/"
    private const val hasTag = true
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(StringConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    fun <T> creat(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> creat(): T = creat(T::class.java)


    private class StringConverter : Converter<ResponseBody, String> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): String {
            return value.string()
        }

        companion object {
            val INSTANCE = StringConverter()
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                if (!hasTag) return@Logger
                Log.d(TAG, "OkHttp====Message: $message")
            })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder() //设置超时
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(
                DEFAULT_TIMEOUT,
                TimeUnit.SECONDS
            )
            .addInterceptor(loggingInterceptor)
            .build()

    }

    private class StringConverterFactory : Converter.Factory() {
        // 我们只关实现从ResponseBody 到 String 的转换，所以其它方法可不覆盖
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (type === String::class.java) {
                StringConverter.INSTANCE
            } else null
            //其它类型我们不处理，返回null就行
        }

        companion object {
            private val INSTANCE = StringConverterFactory()
            fun create(): StringConverterFactory {
                return INSTANCE
            }
        }
    }
}