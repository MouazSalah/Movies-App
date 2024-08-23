package com.paymob.movies.db.api

import com.paymob.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request : Request.Builder =  buildAPIRequest(chain)

        return chain.proceed(request.build())
    }
}

fun buildAPIRequest(chain: Interceptor.Chain): Request.Builder {
    return chain.request().newBuilder()
        .header("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
        .header("accept", "application/json")
}