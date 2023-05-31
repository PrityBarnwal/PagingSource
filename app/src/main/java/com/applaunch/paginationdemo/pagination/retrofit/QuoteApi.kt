package com.applaunch.paginationdemo.pagination.retrofit

import com.applaunch.paginationdemo.pagination.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/quotes")
    suspend fun getQuotes(@Query ("page") page:Int) :QuoteList
}