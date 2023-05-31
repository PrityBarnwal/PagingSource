package com.applaunch.paginationdemo.pagination.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.applaunch.paginationdemo.pagination.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val repository: QuoteRepository):ViewModel() {

    val list = repository.getQuotes().cachedIn(viewModelScope)
}