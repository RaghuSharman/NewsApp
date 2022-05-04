package com.tensorware.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tensorware.newsapp.BottomMenuScreen
import com.tensorware.newsapp.MockData
import com.tensorware.newsapp.components.BottomMenu
import com.tensorware.newsapp.ui.screen.Categories
import com.tensorware.newsapp.ui.screen.DetailScreen
import com.tensorware.newsapp.ui.screen.Sources
import com.tensorware.newsapp.ui.screen.TopNews

/**
 * Created by Raghu N Sharman on 03-05-2022 at 16:05.
 */

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(navController = navController, scrollState = scrollState)
}

@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    Scaffold(bottomBar = {
        BottomMenu(navController = navController)
    }) {
        Navigation(navController = navController, scrollState = scrollState)
    }
}


@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState) {

    NavHost(navController = navController, startDestination = "TopNews") {
        bottomNavigation(navController = navController)

        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable(
            "Detail/{newsId}",
            arguments = listOf(navArgument("newsId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
//            DetailScreen(navController = navController, newsData = newsData)
            DetailScreen(
                newsData = newsData,
                scrollState = scrollState,
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController) {

    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController)
    }
    composable(BottomMenuScreen.Categories.route) {
        Categories()
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources()
    }
}