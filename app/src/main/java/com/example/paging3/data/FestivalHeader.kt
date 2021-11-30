package com.example.paging3.data

import com.google.gson.annotations.SerializedName

data class FestivalHeader(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)