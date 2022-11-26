package com.example.redditnews.MVVM

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.redditnews.Ritrofit.Api
import com.example.redditnews.Ritrofit.retrofit
import com.example.redditnews.Datamodel.DataModel
import com.example.redditnews.Datamodel.UsedData
import com.example.redditnews.Interface.notifiyedDataChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model {
    private  lateinit var api: Api
    private val BASE_URL="https://www.reddit.com"
    private var mArray=ArrayList<UsedData>()
    companion object
    {
       private lateinit var notyfyChange: notifiyedDataChanged
        fun intializeModel(mFragment:Fragment):Model
        {
            notyfyChange=mFragment as notifiyedDataChanged
            return Model()
        }
    }
    private  fun getData()
    {
        api = retrofit.getRetrofit(BASE_URL).create(Api::class.java)
        val call = api.calldata()
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.body() != null) {
                    Log.d(
                        "title",
                        response.body()!!.data.children.get(0).data.title
                    )
                    splitIntoArray( response.body()!!)
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })
    }

   fun liveData():MutableLiveData<List<UsedData>>
   {
      getData()
       var mMutable= MutableLiveData<List<UsedData>>()
       mMutable.postValue(mArray)
       return mMutable
   }

    private fun splitIntoArray(body: DataModel) {
          for (D in body.data.children)
          {
              if(D.data.all_awardings.isEmpty()) {
                  mArray.add(
                      UsedData(
                          D.data.title,
                          D.data.selftext,
                         "null"
                      )
                  )
              }
              else
              {
                  mArray.add(
                      UsedData(
                          D.data.title,
                          D.data.selftext,
                          D.data.all_awardings.get(0).icon_url
                      )
                  )
              }
          }
        notyfyChange.dataChanged()
    }
}