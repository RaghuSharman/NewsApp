package com.tensorware.newsapp.data

import com.tensorware.newsapp.network.NewsManager

class Repository(val manager: NewsManager) {

    suspend fun getArticles() = manager.getArticles("us")
    suspend fun getArticlesByCategory(category: String) = manager.getArticleByCategory(category = category)
}