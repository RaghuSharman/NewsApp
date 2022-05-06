package com.tensorware.newsapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Raghu N Sharman on 05-05-2022 at 15:26.
 */
object Api {

    private const val BASE_URL = "https://newsapi.org/v2/"
    public const val API_KEY = "717d3e541fa1492e8aa2e7f163972bf5"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }
}