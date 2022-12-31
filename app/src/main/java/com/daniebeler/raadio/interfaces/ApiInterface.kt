package com.daniebeler.raadio.interfaces

import com.daniebeler.raadio.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("todos/1")
    fun sendReq() : Call<ResponseModel>
}