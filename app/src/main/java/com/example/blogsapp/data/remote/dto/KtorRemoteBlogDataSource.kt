package com.example.blogsapp.data.remote.dto

import com.example.blogsapp.data.util.Contant.GITHUB_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.example.blogsapp.data.util.Result
import io.ktor.client.statement.bodyAsText
import java.net.UnknownHostException


class KtorRemoteBlogDataSource(
    private val httpClient:HttpClient
) :RemoteBlogDataSource{

     override suspend fun getAllBlogs():Result<List<BlogDto>>{
        return  try {
            val response=httpClient.get(urlString=GITHUB_URL)
            val blogs=response.body<List<BlogDto>>()
            Result.Success(blogs)
        }catch (e:UnknownHostException){
            e.printStackTrace()
            Result.Error(" Network Error Please verify your internet connection ${e.message}")
        }
        catch (e:Exception){
            e.printStackTrace()
            Result.Error("Something went wrong ${e.message}")
        }
    }

    override suspend fun fetchBlogContent(url: String): Result<String> {
        return  try {
            val response=httpClient.get(urlString=url)
            val blogsContent=response.bodyAsText()
            Result.Success(blogsContent)
        }catch (e:UnknownHostException){
            e.printStackTrace()
            Result.Error(" Network Error Please verify your internet connection ${e.message}")
        }
        catch (e:Exception){
            e.printStackTrace()
            Result.Error("Something went wrong ${e.message}")
        }
    }
}