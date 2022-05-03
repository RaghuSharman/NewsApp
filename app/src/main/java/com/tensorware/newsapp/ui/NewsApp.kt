package com.tensorware.newsapp.ui

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tensorware.newsapp.MockData
import com.tensorware.newsapp.ui.screen.DetailScreen
import com.tensorware.newsapp.ui.screen.TopNews

/**
 * Created by Raghu N Sharman on 03-05-2022 at 16:05.
 */

@Composable
fun NewsApp() {
    Navigation()
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()


    NavHost(navController = navController, startDestination = "TopNews") {
        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable(
            "Detail/{newsId}",
            arguments = listOf(navArgument("newsId") {
                type = NavType.IntType
            })
        ) {
            navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
//            DetailScreen(navController = navController, newsData = newsData)
            DetailScreen(newsData = newsData, scrollState = scrollState)
        }
    }
}