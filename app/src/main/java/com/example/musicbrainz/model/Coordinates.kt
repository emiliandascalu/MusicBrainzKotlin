package com.example.musicbrainz.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//    "coordinates": {
//    "latitude": "40.415364",
//    "longitude": "-3.707398"
//},

@JsonClass(generateAdapter = true)
data class Coordinates (
    @Json(name="latitude")
    var latitude: Double? = null,
    @Json(name="longitude")
    var longitude: Double? = null
)