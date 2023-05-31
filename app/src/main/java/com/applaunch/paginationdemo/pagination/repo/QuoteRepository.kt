package com.applaunch.paginationdemo.pagination.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.applaunch.paginationdemo.pagination.paging.QuotePagingSource
import com.applaunch.paginationdemo.pagination.retrofit.QuoteApi
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteApi: QuoteApi) {

    fun getQuotes() = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { QuotePagingSource(quoteApi) }
    ).liveData
}