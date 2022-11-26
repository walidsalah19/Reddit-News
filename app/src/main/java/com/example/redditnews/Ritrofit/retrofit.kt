package com.example.redditnews.Ritrofit

import com.example.redditnews.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class retrofit {
    companion object {
       // lateinit var retrofit: Retrofit
         fun getRetrofit(BASE_URL:String):Retrofit {
           val logging = HttpLoggingInterceptor()
           logging.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

           val client = OkHttpClient.Builder()
               .addInterceptor(logging)
               .connectTimeout(1, TimeUnit.MINUTES)
               .readTimeout(20, TimeUnit.SECONDS)
               .writeTimeout(20, TimeUnit.SECONDS)
               .build()

           val gson = GsonBuilder()
               .setLenient()
               .create()

          var retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .client(client)
               .build()

           return retrofit
        }
    }

}