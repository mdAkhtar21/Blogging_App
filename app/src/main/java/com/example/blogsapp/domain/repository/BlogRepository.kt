package com.example.blogsapp.domain.repository

import com.example.blogsapp.data.util.Result
import com.example.blogsapp.domain.model.Blog

interface BlogRepository {
    suspend fun getAllBlogs():Result<List<Blog>>
    suspend fun getBlogById(blogId:Int):Result<Blog>
}