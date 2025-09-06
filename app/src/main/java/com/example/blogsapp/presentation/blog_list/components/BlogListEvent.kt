package com.example.blogsapp.presentation.blog_list.components

sealed class BlogListEvent {
    data class Error(val error:String):BlogListEvent()
}