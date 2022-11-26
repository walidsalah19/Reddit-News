package com.example.redditnews

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditnews.MVVM.viewModel
import com.example.redditnews.dataModels.*
import com.example.redditnews.databinding.ActivityMainBinding
import com.example.redditnews.room.database
import com.example.redditnews.room.entity

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: viewModel
    private lateinit var mbinding:ActivityMainBinding
    private  var madapet:recyclerViewAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        defineToolBar()
        defineViewModel()
        offlineOrOnline()
        defineRecyclerView()

    }
    private fun defineToolBar()
    {
        mbinding.toolbar.title="Kotlin News"
        setSupportActionBar(mbinding.toolbar)
    }
    private fun defineViewModel()
    {
        mViewModel= ViewModelProvider(this).get(viewModel::class.java)
    }
    private fun defineRecyclerView()
    {

        mbinding.recyclerView.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
    }
    private fun offlineOrOnline()
    {
        if(checkForInternet(this))
        {
            mViewModel.intialViewModel(MainActivity())
            Log.d("net","true")
            mViewModel.getData().observe(this) {
                Log.d("this","adsdasdasdasdsadasdas")
                madapet = recyclerViewAdapter(it,this@MainActivity)
                mbinding.recyclerView.adapter=madapet
                madapet?.notifyDataSetChanged()
                intserLocalDataBase(it)
            }
        }
        else{
            getLocalDatabase()
        }
    }

    private fun getLocalDatabase() {
        val mdatabase=database.getInstance(this)
        var childrenArrayList=ArrayList<children>()
        var all_awardings=ArrayList<media>()
        var list= mdatabase?.mDao()?.getlist()
        if (list != null) {
            for(ch in list)
            {
                if(ch.icon_url.equals("null")) {
                    childrenArrayList.add(
                        children(
                            ChildrenData(
                                ch.title,
                                ch.selftext,
                                all_awardings
                            )
                        )
                    )
                }
                else
                {
                    all_awardings.add(media(ch.icon_url))
                    childrenArrayList.add(
                        children(
                            ChildrenData(
                                ch.title,
                                ch.selftext,
                                all_awardings
                            )
                        )
                    )
                }
            }
            var mdataclass=dataclass(data(childrenArrayList))
            madapet = recyclerViewAdapter(mdataclass,this@MainActivity)
            mbinding.recyclerView.adapter=madapet
            madapet?.notifyDataSetChanged()
        }

    }

    private fun intserLocalDataBase(it: dataclass?) {
       var mdatabase=database.getInstance(this)
       /* for (ch in it?.data?.children!!) {
            if (ch.data.all_awardings.isEmpty()) {
                mdatabase?.Dao()?.insertNews(
                    entity(
                        1,
                        ch.data.title,
                        ch.data.selftext,
                        "null"
                    )
                )
            }
            else
            {
                mdatabase?.Dao()?.insertNews(
                    entity(
                        1,
                        ch.data.title,
                        ch.data.selftext,
                        ch.data.all_awardings.get(0).icon_url
                    )
                )
            }
        }*/
        mdatabase?.mDao()?.insertNews(
            entity(
                1,
                "walid",
               "walid",
                "null"
            )
        )
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
