package com.applaunch.paginationdemo.navigation

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.applaunch.paginationdemo.databinding.FragmentSettingBinding
import com.applaunch.paginationdemo.navigation.model.MovieResponse
import com.applaunch.paginationdemo.navigation.model.Result
import com.applaunch.paginationdemo.navigation.service.ApiService
import com.applaunch.paginationdemo.navigation.service.MovieApiService
import com.applaunch.paginationdemo.pagination.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override fun FragmentSettingBinding.initialize() {

        recSetting.layoutManager = LinearLayoutManager(requireContext())
        recSetting.setHasFixedSize(true)
        getMovieData { movies: List<Result> ->
            recSetting.adapter = SettingAdapter(movies)
        }
    }
    private fun getMovieData(callback: (List<Result>) -> Unit){
        val apiService = MovieApiService.getInstance().create(ApiService::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                return callback(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}