package com.example.redditnews.MVVM

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditnews.Datamodel.UsedData

class ViewModel : ViewModel() {
    private  var mDataClass= MutableLiveData<List<UsedData>>()
    fun intialViewModel(mFragment: Fragment)
    {
        var mmodel=Model.intializeModel(mFragment)
        mDataClass=mmodel.liveData()
        Log.d("error", "er4")
    }
    fun getData(): MutableLiveData<List<UsedData>> {
        return mDataClass
    }
}