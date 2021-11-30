package com.example.paging3.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.paging3.api.FestivalService
import com.example.paging3.data.FestivalItem
import com.example.paging3.data.FestivalItems
import com.example.paging3.data.ResFestival
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private const val FESTIVAL_STARTING_PAGE_INDEX = 1
private const val FESTIVAL_LOADSIZE = 20
class FestivalPagingSource(private val service: FestivalService, private val serviceKey: String) : RxPagingSource<Int, FestivalItem>() {
    override fun getRefreshKey(state: PagingState<Int, FestivalItem>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FestivalItem>> {
        val nextPageNumber = params.key ?: FESTIVAL_STARTING_PAGE_INDEX

        return service.getFestivalList(serviceKey = serviceKey, pageNo = nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it, nextPageNumber)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ResFestival, nextPageNumber: Int): LoadResult<Int, FestivalItem> {

        val totalPage = data.resFestival.body.totalCount / FESTIVAL_LOADSIZE

        return LoadResult.Page(
            data = data.resFestival.body.items.item,
            prevKey = if(nextPageNumber == FESTIVAL_STARTING_PAGE_INDEX) null else nextPageNumber - 1,
            nextKey = if(nextPageNumber < totalPage) nextPageNumber + 1 else null
        )
    }


}