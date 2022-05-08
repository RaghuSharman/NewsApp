package com.tensorware.newsapp.data

import com.tensorware.newsapp.network.NewsManager

class Repository(val manager: NewsManager) {

    suspend fun getArticles() = manager.getArticles("us")
    suspend fun getArticlesByCategory(category: String) =
        manager.getArticleByCategory(category = category)


    suspend fun getArticlesBySource(source: String) = manager.getArticlesBySource(source = source)

    suspend fun getSearchedArticles(query: String) = manager.getSearchedArticles(query = query)
}