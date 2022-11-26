package com.example.redditnews.Ritrofit

import com.example.redditnews.dataModels.dataclass
import retrofit2.Call
import retrofit2.http.GET

interface Api {
  @GET("/r/kotlin/.json")
   fun calldata(): Call<dataclass>

}