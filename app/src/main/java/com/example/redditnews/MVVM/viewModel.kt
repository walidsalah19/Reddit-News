package com.example.redditnews.MVVM

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditnews.MainActivity
import com.example.redditnews.dataModels.dataclass

class viewModel:ViewModel() {
    private  var mdataclass= MutableLiveData<dataclass>()
    fun intialViewModel(mactivity: MainActivity)
    {
        var mmodel=model.intializeModel(mactivity)
        mdataclass=mmodel.liveData()
        Log.d("error", "er4")
    }
    fun getData(): MutableLiveData<dataclass> {
        return mdataclass
    }
}