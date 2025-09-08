package com.example.blogsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blogsapp.data.local.entity.BlogContentEntity
import com.example.blogsapp.data.local.entity.BlogEntity

@Database(
    entities = [BlogEntity::class, BlogContentEntity::class], version = 2
)
abstract class BlogDatabase:RoomDatabase() {
    abstract fun blogDao():BlogDao
}