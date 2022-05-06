package com.tensorware.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.tensorware.newsapp.model.TopNewsResponse
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

    init {
        getArticles()
    }

    private fun getArticles() {
        val service = Api.retrofitService.getTopArticles(
            "US",
            Api.API_KEY
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

}