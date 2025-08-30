package com.example.blogsapp.presentation.blog_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.presentation.blog_list.components.BlogCard


@Composable
fun BlogListScreen(
    modifier: Modifier = Modifier,
    state: BlogListState
){
    Column (
        modifier = modifier.fillMaxSize(),
    ){
        BlogListTopBar()
        LazyVerticalGrid(
            columns =
                GridCells.Adaptive(minSize = 300.dp),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.blogs) { blog ->
                BlogCard(blog = blog)
            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogListTopBar(
    modifier: Modifier=Modifier
){
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = {
            Text(text = "Android Blogs")
        }
    )
}

@Preview
@Composable
private fun BlogListPreview() {
    val dummydata= listOf(
        Blog(
            id = 1,
            title = "Statemanagement",
            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4wkQx_hqXFkKE0X6L_EO6xVGXCK-qxxZMjg&s",
            contentUrl = "",
            content = ""
        )
    )
    BlogListScreen(state = BlogListState(blogs = dummydata))
}