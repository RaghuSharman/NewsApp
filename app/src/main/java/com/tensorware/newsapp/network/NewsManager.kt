package com.tensorware.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.tensorware.newsapp.model.ArticleCategory
import com.tensorware.newsapp.model.TopNewsResponse
import com.tensorware.newsapp.model.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Raghu N Sharman on 05-05-2022 at 15:38.
 */
class NewsManager {


    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    val sourceName = mutableStateOf("abc-news")


    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

    private val _getArticleBySource = mutableStateOf(TopNewsResponse())
    val getArticleBySource: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleBySource
        }

    val query = mutableStateOf("")


    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)

    init {
        getArticles()
    }

    private val _searchedNewsResponse = mutableStateOf(TopNewsResponse())
    val searchedNewsResponse: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _searchedNewsResponse
        }


    private fun getArticles() {
        val service = Api.retrofitService.getTopArticles(
            "US",
//            Api.API_KEY
        )


        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _newsResponse.value = response.body()!!
                    Log.d("news", "${_newsResponse.value}")
                } else {
                    Log.d("error", "${response.body()}")
                }


            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {

                Log.d("error", "${t.printStackTrace()}")
            }


        })
    }


     fun getArticleByCategory(category: String) {
        val service = Api.retrofitService.getArticlesByCategory(category = category,
//            Api.API_KEY
        )

        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _getArticleByCategory.value = response.body()!!
                    Log.d("news", "${_getArticleByCategory.value}")
                } else {
                    Log.d("error", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {

                Log.d("error", "${t.printStackTrace()}")
            }


        })
    }



    fun getArticleBySource() {
        val service = Api.retrofitService.getArticlesBySource(sourceName.value,
        )

        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _getArticleBySource.value = response.body()!!
                    Log.d("news", "${_getArticleBySource.value}")
                } else {
                    Log.d("error", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {

                Log.d("error", "${t.printStackTrace()}")
            }


        })
    }

    fun getSearchedArticles(query: String) {
        val service = Api.retrofitService.getArticles(query,)

        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    _searchedNewsResponse.value = response.body()!!
                    Log.d("Search", "${_searchedNewsResponse.value}")
                } else {
                    Log.d("Search error", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {

                Log.d("Search error", "${t.printStackTrace()}")
            }


        })
    }







    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category = category)
        selectedCategory.value = newCategory
    }

}