package com.tensorware.newsapp

import android.app.Application
import com.tensorware.newsapp.data.Repository
import com.tensorware.newsapp.network.Api
import com.tensorware.newsapp.network.NewsManager

class MainApp : Application() {

    private val manager by lazy {
        NewsManager(Api.retrofitService)
    }

    val repository by lazy {
        Repository(manager = manager)
    }

}