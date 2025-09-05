package com.example.blogsapp.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blogsapp.data.local.BlogDao

@Database(
    entities = [BlogEntity::class], version = 1
)
abstract class BlogDatabase:RoomDatabase() {
    abstract fun blogDao():BlogDao
}