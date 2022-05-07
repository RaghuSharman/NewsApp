package com.tensorware.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import com.tensorware.newsapp.MockData
import com.tensorware.newsapp.MockData.getTimeAgo
import com.tensorware.newsapp.R
import com.tensorware.newsapp.components.SearchBar
import com.tensorware.newsapp.model.TopNewsArticle
import com.tensorware.newsapp.network.NewsManager
import com.tensorware.newsapp.ui.theme.NewsAppTheme

/**
 * Created by Raghu N Sharman on 03-05-2022 at 14:31.
 */

@Composable
fun TopNews(
    navController: NavController,
    articles: List<TopNewsArticle>,
    query: MutableState<String>,
    newsManager: NewsManager
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
//        Button(onClick = {
//            navController.navigate("DetailScreen")
//        }) {
//            Text(text = "Go to Detail Screen")
//        }
        SearchBar(query = query, newsManager = newsManager)

        val searchedText = query.value
        val resultList = mutableListOf<TopNewsArticle>()
        if (searchedText != "") {
            resultList.addAll(newsManager.searchedNewsResponse.value.articles ?: articles)
        } else {
            resultList.addAll(articles)
        }


        LazyColumn {
            items(resultList.size) { index ->
                TopNewsItem(
                    article = resultList[index],
                    onNewsClick = { navController.navigate("Detail/$index") }

                )
            }
        }
    }
}


@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news)
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            article.publishedAt?.let {
                Text(
                    text = MockData.stringToDate(article.publishedAt).getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }


            Spacer(modifier = Modifier.height(80.dp))
            article.title?.let {
                Text(
                    text = article.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewPreview() {
    NewsAppTheme {
        TopNewsItem(
            TopNewsArticle(
                author = "Raja Razek, CNN",
                title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
                description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
                publishedAt = "2021-11-04T05:35:21Z"
            )
        )
    }
}