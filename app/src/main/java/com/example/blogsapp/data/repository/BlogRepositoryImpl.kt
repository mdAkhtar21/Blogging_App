package com.example.blogsapp.data.repository

import com.example.blogsapp.data.mapper.toBlogList
import com.example.blogsapp.data.remote.dto.HttpClientFactory
import com.example.blogsapp.data.remote.dto.KtorRemoteBlogDataSource
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.domain.repository.BlogRepository
import io.ktor.client.engine.okhttp.OkHttp

class BlogRepositoryImpl(private val remoteBlogDataSource: KtorRemoteBlogDataSource):BlogRepository {


    override suspend fun getAllBlogs(): List<Blog>? {
        return  remoteBlogDataSource.getAllBlogs()?.toBlogList()
    }
}