package com.example.musicbrainz.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//    "life-span": {
//    "begin": "1790",
//    "ended": null
//}

@JsonClass(generateAdapter = true)
data class LifeSpan (
    @Json(name="begin")
    var begin: String? = null,
    @Json(name="ended")
    var ended: Boolean? = null
)