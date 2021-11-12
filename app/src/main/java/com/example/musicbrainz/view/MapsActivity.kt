package com.example.musicbrainz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.musicbrainz.R
import com.example.musicbrainz.model.GetPlacesResponse
import com.example.musicbrainz.model.Place
import com.example.musicbrainz.viewmodel.PlacesViewModel

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var placesViewModel: PlacesViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        placesViewModel = ViewModelProviders.of(this).get(PlacesViewModel::class.java)

        val searchText : EditText = findViewById(R.id.search_text)
        val searchButton : Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener { getPlaces(searchText.text.toString()) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun getPlaces(searchText : String) {
        placesViewModel?.placesLiveData?.observe(this, Observer { getPlacesResponse ->
            if (getPlacesResponse == null) {
                Toast.makeText(this, "No places found", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            Log.e("Music", "Received " + (getPlacesResponse.places?.size) + " new places")
            logPlaces(getPlacesResponse)
            addMarkersForPlaces(getPlacesResponse)
        })

        placesViewModel?.getPlacesWithFlow(searchText)
    }

    private fun addMarkersForPlaces(getPlacesResponse: GetPlacesResponse) {
        val places: List<Place>? = getPlacesResponse.places
        places?.forEach {
            if (it.coordinates != null && it.coordinates!!.latitude != null && it.coordinates!!.longitude != null) {
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            it.coordinates!!.latitude!!,
                            it.coordinates!!.longitude!!
                        )
                    ).title(it.name)
                )
            }
        }
    }

    private fun logPlaces(getPlacesResponse: GetPlacesResponse) {
        getPlacesResponse.places?.forEach {
            Log.d("Music", "\t" + it.name)
        }
    }
}
