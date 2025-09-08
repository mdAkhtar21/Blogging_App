package com.example.blogsapp.data.remote.dto

import com.example.blogsapp.data.util.Result
interface RemoteBlogDataSource {
    suspend fun getAllBlogs():Result<List<BlogDto>>
    suspend fun fetchBlogContent(url:String):Result<String>
}