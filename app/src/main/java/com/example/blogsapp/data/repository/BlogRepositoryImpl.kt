package com.example.blogsapp.data.repository

import com.example.blogsapp.data.local.BlogDao
import com.example.blogsapp.data.mapper.toBlogEntityList
import com.example.blogsapp.data.mapper.toBlogList
import com.example.blogsapp.data.remote.dto.KtorRemoteBlogDataSource
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val remoteBlogDataSource: KtorRemoteBlogDataSource,
    private val localBlogDataSource:BlogDao
):BlogRepository {

    override suspend fun getAllBlogs(): List<Blog>? {
        val remoteBlogs=remoteBlogDataSource.getAllBlogs()
        return if(remoteBlogs!=null){
            localBlogDataSource.deleteAllBlogs()
            localBlogDataSource.insertBlogs(remoteBlogs.toBlogEntityList())
            remoteBlogs.toBlogList()
        }
        else{
            val localBlogs=localBlogDataSource.getAllBlogs()
            if(localBlogs.isNotEmpty()){
                localBlogs.toBlogList()
            }else{
                null
            }
        }
    }
}