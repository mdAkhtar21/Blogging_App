package com.example.blogsapp.domain.repository

import com.example.blogsapp.domain.model.Blog

interface BlogRepository {
    suspend fun getAllBlogs():List<Blog>?
}