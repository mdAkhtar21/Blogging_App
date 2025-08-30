package com.example.blogsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.presentation.blog_list.BlogListScreen
import com.example.blogsapp.presentation.blog_list.BlogListState
import com.example.compose.BlogsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val dummydata= listOf(
                        Blog(
                            id = 1,
                            title = "Statemanagement",
                            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4wkQx_hqXFkKE0X6L_EO6xVGXCK-qxxZMjg&s",
                            contentUrl = "",
                            content = ""
                        )
                    )
                    BlogListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = BlogListState(blogs=dummydata)
                    )
                }
            }
        }
    }
}

