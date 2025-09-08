package com.example.blogsapp.di

import com.example.blogsapp.data.local.BlogDatabase
import com.example.blogsapp.data.local.DatabaseFactory
import com.example.blogsapp.data.remote.dto.HttpClientFactory
import com.example.blogsapp.data.remote.dto.RemoteBlogDataSource
import com.example.blogsapp.data.remote.dto.KtorRemoteBlogDataSource
import com.example.blogsapp.data.repository.BlogRepositoryImpl
import com.example.blogsapp.domain.repository.BlogRepository
import com.example.blogsapp.presentation.blog_list.BlogListViewModel
import com.example.blogsapp.presentation.blog_content.BlogContentViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinMainModule = module {

    single { DatabaseFactory.onCreate(get()) }
    single { get<BlogDatabase>().blogDao() }
    single { HttpClientFactory.create(OkHttp.create()) }

    singleOf(::KtorRemoteBlogDataSource).bind<RemoteBlogDataSource>()
    singleOf(::BlogRepositoryImpl).bind<BlogRepository>()

    viewModelOf(::BlogListViewModel)
    viewModelOf(::BlogContentViewModel)
}
