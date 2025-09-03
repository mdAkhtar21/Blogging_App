package com.example.blogsapp.data.mapper

import com.example.blogsapp.data.remote.dto.BlogDto
import com.example.blogsapp.domain.model.Blog


fun BlogDto.toBlog()= Blog(
    id=id,
    title=title,
    thumbnailUrl=thumbnailUrl,
    contentUrl=contentUrl,
    content=null
)

fun List<BlogDto>.toBlogList()=map{
    it.toBlog()
}