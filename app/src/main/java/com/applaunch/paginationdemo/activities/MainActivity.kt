package com.applaunch.paginationdemo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.applaunch.paginationdemo.databinding.ActivityMainBinding
import com.applaunch.paginationdemo.pagination.interfaces.listenerMinus
import com.applaunch.paginationdemo.pagination.interfaces.listenerPlus
import com.applaunch.paginationdemo.pagination.paging.QuotePagingAdapter
import com.applaunch.paginationdemo.pagination.viewModel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: QuotePagingAdapter
    lateinit var quoteViewModel: QuoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = QuotePagingAdapter()
        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]

        binding.quoteList.layoutManager = LinearLayoutManager(this)
        binding.quoteList.setHasFixedSize(true)
        binding.quoteList.adapter = adapter

        quoteViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })

        listenerPlus = { _, _, item ->
            if (item.count < 10) {
                item.count++
            }
        }
        listenerMinus ={_, _, item ->
            if (item.count > 0){
                item.count--
            }
        }
    }
}