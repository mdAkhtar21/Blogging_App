package com.example.blogsapp.data.mapper

import com.example.blogsapp.data.local.entity.BlogEntity
import com.example.blogsapp.domain.model.Blog


fun BlogEntity.toBlog(
    content:String?=null)= Blog(
        id=id,
        title=title,
        thumbnailUrl=thumbnailUrl,
        contentUrl=contentUrl,
        content=content
)

fun List<BlogEntity>.toBlogList()= map{it.toBlog()}