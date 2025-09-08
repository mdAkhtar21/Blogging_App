package com.example.blogsapp.presentation.blog_content

import com.example.blogsapp.domain.model.Blog

data class BlogContentState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val blog: Blog?=null
)
