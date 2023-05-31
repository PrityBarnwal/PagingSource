package com.applaunch.paginationdemo.pagination.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.applaunch.paginationdemo.pagination.model.Result
import com.applaunch.paginationdemo.pagination.retrofit.QuoteApi

class QuotePagingSource(val quoteApi: QuoteApi) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val position = params.key ?: 1
            val response = quoteApi.getQuotes(position)
            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

//        if (state.anchorPosition != null){
//            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
//            if (anchorPage?.prevKey != null){
//                return anchorPage.prevKey!!.plus(1)
//            }else if (anchorPage?.nextKey != null){
//                return anchorPage.nextKey!!.minus(1)
//            }
//        }else{
//            return null
//        }
    }
}