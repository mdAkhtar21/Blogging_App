package com.example.blogsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blogsapp.data.util.Contant.BLOG_CONTENT_TABLE_NAME

@Entity(tableName = BLOG_CONTENT_TABLE_NAME)
data class BlogContentEntity(
    @PrimaryKey
    val blogId:Int,
    val content:String
)