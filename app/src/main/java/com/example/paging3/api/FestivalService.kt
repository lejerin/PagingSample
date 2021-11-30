package com.example.paging3.api

import com.example.paging3.constant.FESTIVAL_BASE_URL
import com.example.paging3.data.ResFestival
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface FestivalService {
    @GET("areaBasedList")
    fun getFestivalList(
        @Query("serviceKey") serviceKey: String,
        @Query("contentTypeId") contentTypeId: String = "15",
        @Query("listYN") listYN: String = "Y",
        @Query("MobileOS") MobileOS: String = "AND",
        @Query("MobileApp") MobileApp: String = "CertiApp",
        @Query("arrange") arrange: String = "A",
        @Query("numOfRows") numOfRows: String = "20",
        @Query("pageNo") pageNo: Int,
        @Query("_type") _type: String = "json"
    ): Single<ResFestival>

    companion object{
        fun getApiClinet(): FestivalService {
            return Retrofit.Builder()
                .baseUrl(FESTIVAL_BASE_URL)
                .client(provideLogging())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(FestivalService::class.java)
        }

        private fun provideLogging(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient().newBuilder()
                .connectTimeout(1500, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        }
    }
}