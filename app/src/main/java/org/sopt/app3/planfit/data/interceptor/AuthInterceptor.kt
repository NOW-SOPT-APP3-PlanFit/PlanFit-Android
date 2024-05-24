package org.sopt.app3.planfit.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().addHeader("user_id","1").build())
    }
}