package com.example.blogsapp.presentation.blog_content

sealed interface BlogContentAction {
    data object Refresh:BlogContentAction
}