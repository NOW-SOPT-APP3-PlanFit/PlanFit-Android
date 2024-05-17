package org.sopt.app3.planfit.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.app3.planfit.data.api.ExerciseListService
import org.sopt.app3.planfit.data.interceptor.AuthInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL = "BuildConfig.BASE_URL"

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthInterceptor())
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }


    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val exerciseListService by lazy { ApiFactory.create<ExerciseListService>() }
}