package com.example.paging3.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FestivalItem(
    @SerializedName("addr1") val addr1: String,
    @SerializedName("areacode") val areacode: String,
    @SerializedName("contentid") val contentid: String,
    @SerializedName("firstimage") val firstimage: String,
    @SerializedName("title") val title: String,
) : Serializable