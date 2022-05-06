package com.tensorware.newsapp.model

import com.tensorware.newsapp.model.ArticleCategory.*

/**
 * Created by Raghu N Sharman on 06-05-2022 at 14:43.
 */


enum class ArticleCategory(val categoryName: String) {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}

fun getAllArticleCategory(): List<ArticleCategory> {
    return listOf(
        ArticleCategory.BUSINESS,
        ArticleCategory.ENTERTAINMENT,
        ArticleCategory.GENERAL,
        ArticleCategory.HEALTH,
        ArticleCategory.SCIENCE,
        ArticleCategory.SPORTS,
        ArticleCategory.TECHNOLOGY
    )
}

fun getArticleCategory(category: String) : ArticleCategory?{
    val map = values().associateBy(ArticleCategory::categoryName)
    return map[category]

}