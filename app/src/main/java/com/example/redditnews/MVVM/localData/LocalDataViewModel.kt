package com.example.redditnews.MVVM.localData

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditnews.Datamodel.UsedData

class LocalDataViewModel : ViewModel() {
    private  var mDataClass= MutableLiveData<List<UsedData>>()
    private  lateinit var mmodel:LocalDataModel
    fun intialViewModel(fragment: Fragment)
    {
        mmodel= LocalDataModel.intializeModel(fragment)
    }
    fun liveData()
    {
        mDataClass=mmodel.liveData()
    }
    fun getData(): MutableLiveData<List<UsedData>> {
        return mDataClass
    }
    fun insertData(it: List<UsedData>)
    {
      mmodel.changeListToItems(it)
    }
}