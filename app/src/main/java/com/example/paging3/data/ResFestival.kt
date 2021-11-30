package com.example.paging3.data

import com.google.gson.annotations.SerializedName

data class ResFestival (
    @SerializedName("response") val resFestival: ResponseFestival
){

}
data class ResponseFestival(
    @SerializedName("body") val body: FestivalBody,
    @SerializedName("header") val header: FestivalHeader
)
