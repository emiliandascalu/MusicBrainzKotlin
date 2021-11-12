package com.example.musicbrainz.viewmodel

import com.example.musicbrainz.model.GetPlacesResponse
import com.example.musicbrainz.networking.RestApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepository {

    val completableJob = Job()

    private val createRestService by lazy {
        RestApiService.createCorService()
    }

    fun getPlacesWithFlow(searchText: String, limit: Int): Flow<GetPlacesResponse> =
        flow {
            val offset1 = 0
            val response = createRestService.getPlaces(searchText, "json", offset1, limit).await()
            var count = response.count
            var sad = 0L
//            while (count?.minus(offset1)!! >= 0) {
//                emit(response)
//                offset1 += 10
//                response = createRestService.getPlaces(searchText, "json", offset1, limit).await()
//                count = response.count
//            }
        }
}