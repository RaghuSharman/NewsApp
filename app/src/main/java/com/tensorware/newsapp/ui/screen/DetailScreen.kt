package com.tensorware.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tensorware.newsapp.MockData
import com.tensorware.newsapp.MockData.getTimeAgo
import com.tensorware.newsapp.R
import com.tensorware.newsapp.model.NewsData
import com.tensorware.newsapp.ui.theme.NewsAppTheme

/**
 * Created by Raghu N Sharman on 03-05-2022 at 15:30.
 */

@Composable
//
fun DetailScreen(newsData: NewsData, scrollState: ScrollState, navController: NavController) {
    Scaffold(topBar = { DetailTopAppBar(onBackPressed = { navController.popBackStack() }) }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Details Screen", fontWeight = FontWeight.SemiBold)
//        Button(onClick = {
////            navController.navigate("TopNews")
//
//            // popBackStack -> returns to previous screen which is available in the stack
//            navController.popBackStack()
//        }) {
//            Text(text = "Go to Top News Screen + ${newsData.author}")
//        }
            Image(painter = painterResource(id = newsData.image), contentDescription = "")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//            Icon(imageVector = Icons.Default.Edit, contentDescription = "", modifier = Modifier.padding(start = 10.dp))
//            Text(text = newsData.author,modifier = Modifier.padding(start = 10.dp, end = 10.dp))
//            Icon(imageVector = Icons.Default.Call, contentDescription = "",modifier = Modifier.padding(end = 10.dp))
//            Text(text = newsData.publishedAt, )

                InfoWithIcon(icon = Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(
                    icon = Icons.Default.DateRange,
                    info = MockData.stringToDate(newsData.publishedAt).getTimeAgo()
                )
            }
            Text(
                text = newsData.title,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = newsData.description,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                fontWeight = FontWeight.Light
            )
        }
    }

}

@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        })

}


@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {

    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            colorResource(id = R.color.purple_200)
        )
        Text(text = info, modifier = Modifier.padding(end = 8.dp))
    }

}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    NewsAppTheme {
        DetailScreen(
            NewsData(
                1,
                author = "Raja Razek, CNN",
                title = "'Tiger King' Joe Exotic says he has been diagnosed with aggressive form of prostate cancer - CNN",
                description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix docuseries \\\"Tiger King: Murder, Mayhem and Madness,\\\" has been diagnosed with an aggressive form of prostate cancer, according to a letter written by Maldonado.",
                publishedAt = "2021-11-04T05:35:21Z"
            ),
            rememberScrollState(), rememberNavController()
        )
    }
}