package com.example.musicbrainz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.musicbrainz.model.GetPlacesResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlacesViewModel : ViewModel() {

    private val LIMIT = 10
    private val placesRepository = PlacesRepository()

    private val mutablePlacesLiveData = MutableLiveData<GetPlacesResponse>()
    private var getPlacesJob: Job? = null

    val placesLiveData = mutablePlacesLiveData

    fun getPlacesWithFlow(searchText: String) {
        if (getPlacesJob != null && getPlacesJob!!.isActive) {
            getPlacesJob?.cancel()
        }

        getPlacesJob = viewModelScope.launch {
            placesRepository.getPlacesWithFlow(searchText, LIMIT).collect {
                mutablePlacesLiveData.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        placesRepository.completableJob.cancel()
    }
}