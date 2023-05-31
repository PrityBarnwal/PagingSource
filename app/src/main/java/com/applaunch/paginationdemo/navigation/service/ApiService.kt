package com.applaunch.paginationdemo.navigation.service

import com.applaunch.paginationdemo.navigation.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/3/movie/popular?api_key=76072432101bd23b93e66e46f9364a64")
    fun  getMovieList(): Call<MovieResponse>
}

class MovieApiService {
    companion object{
        private  const val BASE_URL="https://api.themoviedb.org"
        private  var retrofit: Retrofit?=null

        fun getInstance():Retrofit{
            if(retrofit==null){
                retrofit=Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}