package com.example.redditnews.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditnews.MVVM.localData.LocalDataViewModel
import com.example.redditnews.MVVM.ViewModel
import com.example.redditnews.databinding.FragmentDiaplayarticalBinding
import com.example.redditnews.Interface.notifiyedDataChanged
import com.example.redditnews.Adapter.recyclerViewAdapter
import com.example.redditnews.Network.Network


class DisplayArtical : Fragment(), notifiyedDataChanged {
    private lateinit var mViewModel: ViewModel
    private lateinit var mLocalModel: LocalDataViewModel

    private  var madapet: recyclerViewAdapter?=null
    private lateinit var mbinding: FragmentDiaplayarticalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mbinding = FragmentDiaplayarticalBinding.inflate(inflater, container, false)
        defineViewModel()
        defineToolBar()
        offlineOrOnline()
        defineRecyclerView()
        return mbinding.getRoot()
    }
    private fun defineToolBar()
    {
        mbinding.toolbar.title="Kotlin News"
        //setSupportActionBar(mbinding.toolbar)
    }
    private fun defineViewModel()
    {
        mViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        mLocalModel= ViewModelProvider(this).get(LocalDataViewModel::class.java)
        mLocalModel.intialViewModel(this)
    }
    private fun defineRecyclerView()
    {

        mbinding.recyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext())
        }
    }
    private fun offlineOrOnline()
    {
        if(Network.checkForInternet(requireContext()))
        {
            mViewModel.intialViewModel(this)
            Log.d("net","true")
        }
        else{
            getLocalData()
        }
    }
    fun getLocalData()
    {
        mLocalModel.liveData()
        mLocalModel.getData().observe(viewLifecycleOwner){
            madapet = recyclerViewAdapter(it,this@DisplayArtical)
            mbinding.recyclerView.adapter=madapet
            madapet?.notifyDataSetChanged()
        }

    }
    override fun dataChanged() {
        mViewModel.getData().observe(viewLifecycleOwner) {
            Log.d("this","adsdasdasdasdsadasdas")
            madapet = recyclerViewAdapter(it,this@DisplayArtical)
            mbinding.recyclerView.adapter=madapet
            madapet?.notifyDataSetChanged()
             mLocalModel.insertData(it)
        }
    }
}