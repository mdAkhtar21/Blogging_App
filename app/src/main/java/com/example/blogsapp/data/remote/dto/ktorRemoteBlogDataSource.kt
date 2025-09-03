package com.example.blogsapp.data.remote.dto

import com.example.blogsapp.data.Contant.GITHUB_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ktorRemoteBlogDataSource(
    private val httpClient:HttpClient
) {

    suspend fun getAllBlogs():List<BlogDto>?{
        return  try {
            val response=httpClient.get(urlString=GITHUB_URL)
            response.body<List<BlogDto>>()
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }
}