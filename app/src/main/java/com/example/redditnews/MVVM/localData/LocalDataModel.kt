package com.example.redditnews.MVVM.localData

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.redditnews.Datamodel.UsedData
import com.example.redditnews.room.DatabaseClass
import com.example.redditnews.room.Entity
import java.util.*

class LocalDataModel {
    private var mUsedData= ArrayList<UsedData>()
    companion object
    {
        lateinit var mFragment: Fragment
        fun intializeModel(fragment: Fragment): LocalDataModel
        {
            mFragment=fragment
            return LocalDataModel()
        }
    }
    private fun getLocalDatabase() {
        val mDatabase= DatabaseClass.getInstance(mFragment.requireContext())
        var mEntry=mDatabase.mDao().getlist()

        for(m in mEntry)
        {
            mUsedData.add(UsedData(m.title,m.selftext,m.icon_url))
        }
    }
    fun liveData(): MutableLiveData<List<UsedData>>
    {
        getLocalDatabase()
        var mMutable= MutableLiveData<List<UsedData>>()
        mMutable.postValue(mUsedData)
        return mMutable
    }
    fun changeListToItems(it: List<UsedData>) {
        var mDatabase= DatabaseClass.getInstance(mFragment.requireContext())
        var id:Int=1
        for (ch in it) {
            Log.d("insert",id.toString())
            insertIntoLocalDatabase(
                mDatabase,
                ch.title,
                ch.selftext,
                ch.icon_url)
            id++
        }

    }
    fun insertIntoLocalDatabase(
        mDatabase: DatabaseClass,
        title: String,
        text: String,
        uri: String
    )
    {
        mDatabase.mDao().insertNews(
            Entity(

                title,
                text,
                uri
            )
        )
    }
}