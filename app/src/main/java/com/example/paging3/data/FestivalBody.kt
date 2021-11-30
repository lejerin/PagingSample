package com.example.paging3.data

import com.google.gson.annotations.SerializedName

data class FestivalBody(
    @SerializedName("items")
    val items: FestivalItems,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("totalCount")
    val totalCount: Int

)