package com.tensorware.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tensorware.newsapp.BottomMenuScreen
import com.tensorware.newsapp.components.BottomMenu
import com.tensorware.newsapp.model.TopNewsArticle
import com.tensorware.newsapp.network.Api
import com.tensorware.newsapp.network.NewsManager
import com.tensorware.newsapp.ui.screen.Categories
import com.tensorware.newsapp.ui.screen.DetailScreen
import com.tensorware.newsapp.ui.screen.Sources
import com.tensorware.newsapp.ui.screen.TopNews

/**
 * Created by Raghu N Sharman on 03-05-2022 at 16:05.
 */

@Composable
fun NewsApp(mainViewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(
        navController = navController,
        scrollState = scrollState,
        mainViewModel = mainViewModel
    )
}

@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState, mainViewModel: MainViewModel
) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(
            navController = navController,
            scrollState = scrollState,
            paddingValues = it,
            viewModel = mainViewModel
        )
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager(Api.retrofitService),
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {

    val articles = mutableListOf(TopNewsArticle())
    val topArticles = viewModel.newsResponse.collectAsState().value.articles

    articles.addAll(topArticles ?: listOf())
//        newsManager.newsResponse.value.articles
    Log.d("news", "$articles")

    // articles can be nullable hence surround the entire navHost inside
    // articles?.let
    NavHost(
        navController = navController,
        startDestination =
        BottomMenuScreen.TopNews.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    )


    {
        bottomNavigation(navController = navController, articles, newsManager = newsManager, viewModel = viewModel)

        composable(
            "Detail/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val index = navBackStackEntry.arguments?.getInt("index")
            index?.let {
                if (newsManager.query.value.isNotEmpty()) {

                    articles.clear()
                    articles.addAll(newsManager.searchedNewsResponse.value.articles ?: listOf())
                } else {
                    articles.clear()
                    articles.addAll(viewModel.newsResponse.value.articles ?: listOf())
                }

                val article = articles[index]
                DetailScreen(
                    article = article,
                    scrollState = scrollState,
                    navController = navController
                )
            }
//            DetailScreen(navController = navController, newsData = newsData)

        }
    }

}


fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    article: List<TopNewsArticle>, newsManager: NewsManager, viewModel: MainViewModel
) {

    composable(BottomMenuScreen.TopNews.route) {
        TopNews(
            navController = navController,
            articles = article,
            newsManager.query,
            newsManager = newsManager
        )
    }
    composable(BottomMenuScreen.Categories.route) {
        viewModel.getArticlesByCategory("business")

        viewModel.onSelectedCategoryChanged("business")


        Categories(viewModel = viewModel, onFetchCategory = {
          viewModel.onSelectedCategoryChanged(it)
            viewModel.getArticlesByCategory(it)
        })
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources(newsManager = newsManager)
    }
}