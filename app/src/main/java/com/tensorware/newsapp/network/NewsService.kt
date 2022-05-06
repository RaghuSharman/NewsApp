package com.tensorware.newsapp.network

import com.tensorware.newsapp.model.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Raghu N Sharman on 05-05-2022 at 15:19.
 */

// this is a service interface
interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopNewsResponse>
}