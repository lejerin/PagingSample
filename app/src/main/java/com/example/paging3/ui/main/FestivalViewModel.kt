package com.example.paging3.ui.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.rxjava3.cachedIn
import com.example.paging3.data.FestivalItem
import com.example.paging3.repository.UserRepository
import com.example.paging3.source.FestivalPagingSource
import io.reactivex.rxjava3.core.Flowable

class FestivalViewModel constructor(private val userRepository: UserRepository): ViewModel() {

    var currentSearchResult: Flowable<PagingData<FestivalItem>>? = null
    fun getFestivalPagingSource(serviceKey: String): Flowable<PagingData<FestivalItem>>{
        val newResult = userRepository.getFestivalList(serviceKey)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}

