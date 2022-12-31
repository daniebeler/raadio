package com.daniebeler.raadio.interfaces

import com.daniebeler.raadio.models.Station
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("stations/byname/oe3")
    fun getStationsByName() : Call<List<Station>>
}