package com.example.covidapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apicovid19indonesia-v2.vercel.app/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CovidServiceApi{

    @GET("api/indonesia/provinsi")
    suspend fun getGames() : MutableList<Covid>
}

object GameApi{
    val retrofitServiceApi : CovidServiceApi by lazy {
        retrofit.create(CovidServiceApi::class.java)
    }
}