package com.example.blogsapp.data.local

import android.content.Context
import androidx.room.Room
import com.example.blogsapp.data.util.Contant.BLOG_DATABASE_NAME

object DatabaseFactory {
    fun onCreate(context:Context): BlogDatabase {
        return Room.databaseBuilder(
            context=context.applicationContext,
            klass = BlogDatabase::class.java,
            name = BLOG_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}