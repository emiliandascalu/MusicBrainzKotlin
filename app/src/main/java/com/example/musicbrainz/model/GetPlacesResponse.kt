package com.example.musicbrainz.model

import com.squareup.moshi.Json

//{
//    "created": "2019-11-15T13:21:25.563Z",
//    "count": 125,
//    "offset": 0,
//    "places": [
//    {
//        "id": "42ec8a15-eb6f-4fef-88b4-e9cfcacec7fd",
//        "type": "Venue",
//        "type-id": "cd92781a-a73f-30e8-a430-55d7521338db",
//        "score": 100,
//        "name": "Plaza Mayor",
//        "disambiguation": "Madrid",
//        "address": "Plaza Mayor, 28012 Madrid, Spain",
//        "coordinates": {
//        "latitude": "40.415364",
//        "longitude": "-3.707398"
//    },
//        "area": {
//        "id": "9ddf829c-48ee-4457-a80d-f7370f24f2ec",
//        "type": "City",
//        "type-id": "6fd8f29a-3d0a-32fc-980d-ea697b69da78",
//        "name": "Madrid",
//        "sort-name": "Madrid",
//        "life-span": {
//        "ended": null
//    }
//    },
//        "life-span": {
//        "begin": "1790",
//        "ended": null
//    }
//    }
//    ]
//}

data class GetPlacesResponse (
    @Json(name="count")
    var count: Int? = null ,
    @Json(name="offset")
    var offset: Int = 0,
    @Json(name="places")
    var places: List<Place>? = null
)