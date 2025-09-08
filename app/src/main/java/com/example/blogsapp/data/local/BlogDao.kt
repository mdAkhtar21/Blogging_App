package com.example.blogsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogsapp.data.local.entity.BlogContentEntity
import com.example.blogsapp.data.local.entity.BlogEntity

@Dao
interface BlogDao {

    @Query("SELECT * FROM blogs")
    suspend fun getAllBlogs():List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogs:List<BlogEntity>)

    @Query("SELECT * FROM blogs WHERE id=:blogId")
    suspend fun getBlogsById(blogId:Int):BlogEntity

    @Query("DELETE FROM blogs")
    suspend fun deleteAllBlogs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertblogContent(content:BlogContentEntity)

    @Query ("SELECT * FROM blog_content WHERE blogId=:blogId")
    suspend fun getBlogContent(blogId:Int):BlogContentEntity?
}