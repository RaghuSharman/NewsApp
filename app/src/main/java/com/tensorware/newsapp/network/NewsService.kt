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

    // get by articles
    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country: String,
//        @Query("apiKey") apiKey: String
    ): TopNewsResponse

    // get by category
    @GET("top-headlines")
    suspend fun getArticlesByCategory(
        @Query("category") category: String,
//        @Query("apiKey") apiKey: String
    ): TopNewsResponse


    // getArticlesBySource
    @GET("everything")
    suspend fun getArticlesBySource(@Query("sources") source: String): TopNewsResponse


    // getArticles which queried by user in the search Bar
    @GET("everything")
    suspend fun getArticles(@Query("q") query: String): TopNewsResponse
}