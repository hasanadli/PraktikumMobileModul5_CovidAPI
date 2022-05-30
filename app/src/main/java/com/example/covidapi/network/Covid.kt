package com.example.covidapi.network

import com.squareup.moshi.Json
////title
////thumb
////desc
//author
//tag
//time
data class Covid(
    @Json(name = "provinsi") val provinsi: String,
    @Json(name = "kasus") val kasus: String,
    @Json(name = "dirawat") val dirawat :  String,
    @Json(name = "sembuh") val sembuh :  String,
    @Json(name = "meninggal") val meninggal :  String
)