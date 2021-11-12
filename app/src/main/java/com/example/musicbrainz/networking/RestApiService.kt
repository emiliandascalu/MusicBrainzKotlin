package com.example.musicbrainz.networking

import com.example.musicbrainz.model.GetPlacesResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RestApiService {
//https://musicbrainz.org/ws/2/place/?query=plaza&fmt=json
    @GET("/ws/2/place")
    fun getPlaces(@Query("query") searchQuery: String,
                  @Query("fmt") format: String,
                  @Query("offset") offset :Int,
                  @Query("limit") limit :Int) : Deferred<GetPlacesResponse>

    companion object {

        fun createCorService(): RestApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://musicbrainz.org")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RestApiService::class.java)
        }
    }
}