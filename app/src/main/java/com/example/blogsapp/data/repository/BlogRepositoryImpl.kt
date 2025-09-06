package com.example.blogsapp.data.repository

import com.example.blogsapp.data.local.BlogDao
import com.example.blogsapp.data.mapper.toBlogEntityList
import com.example.blogsapp.data.mapper.toBlogList
import com.example.blogsapp.data.remote.dto.KtorRemoteBlogDataSource
import com.example.blogsapp.data.util.Result
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val remoteBlogDataSource: KtorRemoteBlogDataSource,
    private val localBlogDataSource:BlogDao
):BlogRepository {

    override suspend fun getAllBlogs(): Result<List<Blog>> {
        val remoteBlogs=remoteBlogDataSource.getAllBlogs()
        return when(remoteBlogs) {
            is Result.Success -> {
                remoteBlogs.data?.let { blogs ->
                    localBlogDataSource.deleteAllBlogs()
                    localBlogDataSource.insertBlogs(blogs.toBlogEntityList())
                    Result.Success(data = blogs.toBlogList())
                } ?: Result.Error("data is not found");

            }
            is Result.Error -> {
                val localBlogs = localBlogDataSource.getAllBlogs()
                if (localBlogs.isNotEmpty()) {
                    Result.Error(
                        data = localBlogs.toBlogList(),
                        message = remoteBlogs.message ?: "Failed to fetch in the data"
                    )
                } else {
                    Result.Error(
                        message = remoteBlogs.message
                            ?: "Failed to fetch in the data no cached data is avilable"
                    )
                }
            }
        }
    }
}