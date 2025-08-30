package com.example.blogsapp.presentation.blog_list

import com.example.blogsapp.domain.model.Blog


data class BlogListState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val blogs:List<Blog> = emptyList()
)
