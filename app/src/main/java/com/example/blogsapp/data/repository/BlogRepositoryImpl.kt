package com.example.blogsapp.data.repository

import com.example.blogsapp.data.local.BlogDao
import com.example.blogsapp.data.local.entity.BlogContentEntity
import com.example.blogsapp.data.mapper.toBlog
import com.example.blogsapp.data.mapper.toBlogEntityList
import com.example.blogsapp.data.mapper.toBlogList
import com.example.blogsapp.data.remote.dto.RemoteBlogDataSource
import com.example.blogsapp.data.util.Result
import com.example.blogsapp.domain.model.Blog
import com.example.blogsapp.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
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
                } ?: Result.Error(message = "data is not found");

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

    override suspend fun getBlogById(blogId: Int): Result<Blog> {
        val blogEntity=localBlogDataSource.getBlogsById(blogId)
            ?: return Result.Error(message = "Blog not found in local database.")
        val contentResult=remoteBlogDataSource.fetchBlogContent(blogEntity.contentUrl)
       return when(contentResult){
            is Result.Success ->{
                val blogContentEntity=BlogContentEntity(
                    blogId=blogId,
                    content=contentResult.data?:""
                )
                localBlogDataSource.insertblogContent(blogContentEntity)

                Result.Success(data = blogEntity.toBlog((contentResult.data)))
            }
            is Result.Error ->{
                val contentEntity=localBlogDataSource.getBlogContent(blogId);
                if(contentEntity!=null){
                    Result.Success(data = blogEntity.toBlog(contentEntity.content))
                }else{
                    Result.Error(message = "Failed to Fetch the blog content ${contentResult.message}")
                }
            }
        }
    }
}