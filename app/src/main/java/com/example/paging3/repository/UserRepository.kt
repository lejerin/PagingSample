package com.example.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.paging3.api.FestivalService
import com.example.paging3.data.FestivalItem
import com.example.paging3.source.FestivalPagingSource
import io.reactivex.rxjava3.core.Flowable


class UserRepository constructor(private val service: FestivalService)
{

    fun getFestivalList(serviceKey: String): Flowable<PagingData<FestivalItem>> {
        return Pager(PagingConfig(pageSize = 20)){
            FestivalPagingSource(service,serviceKey)
        }.flowable
    }
}