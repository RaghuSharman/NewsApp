package com.tensorware.newsapp.model

import com.tensorware.newsapp.R

/**
 * Created by Raghu N Sharman on 03-05-2022 at 16:42.
 */
data class NewsData(
    val id: Int,
    val image: Int = R.drawable.breaking_news,
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String

)
