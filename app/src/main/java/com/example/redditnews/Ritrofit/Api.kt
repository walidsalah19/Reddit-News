package com.example.redditnews.Ritrofit

import com.example.redditnews.Datamodel.DataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {
  @GET("/r/kotlin/.json")
    fun calldata(): Call<DataModel>

}