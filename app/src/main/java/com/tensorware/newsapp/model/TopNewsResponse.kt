package com.tensorware.newsapp.model

/**
 * Created by Raghu N Sharman on 05-05-2022 at 15:09.
 */
data class TopNewsResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<TopNewsArticle>? = null
)
