package com.example.redditnews.MVVM

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.redditnews.MainActivity
import com.example.redditnews.Ritrofit.Api
import com.example.redditnews.Ritrofit.retrofit
import com.example.redditnews.dataModels.dataclass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class model {
    private  lateinit var api: Api
    private val BASE_URL="https://www.reddit.com"
    companion object
    {
        fun intializeModel(mactivity:MainActivity):model
        {
            return model()
        }
    }
   fun liveData():MutableLiveData<dataclass>
   {
       var mmutable= MutableLiveData<dataclass>()
       api = retrofit.getRetrofit(BASE_URL).create(Api::class.java)
       val call = api.calldata()
       call.enqueue(object : Callback<dataclass> {
           override fun onResponse(call: Call<dataclass>, response: Response<dataclass>) {
               if (response.body() != null) {
                   Log.d(
                       "title",
                       response.body()!!.data.children.get(0).data.title
                   )
                  mmutable.postValue(response.body()!!)

               }
           }

           override fun onFailure(call: Call<dataclass>, t: Throwable) {
               Log.d("error", t.message.toString())
           }

       })
       return mmutable
   }
}