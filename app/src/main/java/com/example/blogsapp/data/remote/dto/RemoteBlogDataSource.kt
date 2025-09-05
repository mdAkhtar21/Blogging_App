package com.example.blogsapp.data.remote.dto

import com.example.blogsapp.domain.model.Blog

interface RemoteBlogDataSource {
    suspend fun getAllBlogs():List<BlogDto>?
}