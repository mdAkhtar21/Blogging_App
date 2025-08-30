package com.example.blogsapp.domain.model


data class Blog(
    val id:Int,
    val title: String,
    val thumbnailUrl:String,
    val contentUrl:String,
    val content:String?
)
