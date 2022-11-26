package com.example.redditnews.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditnews.R
import com.example.redditnews.Datamodel.UsedData
import com.example.redditnews.UI.DisplayArtical
import com.example.redditnews.UI.DisplayArticalDirections

class recyclerViewAdapter(var mdataclass: List<UsedData>, var fragment: DisplayArtical) : RecyclerView.Adapter<recyclerViewAdapter.help>() {
    class help(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var title=itemView.findViewById<TextView>(R.id.title)
        var image=itemView.findViewById<ImageView>(R.id.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): help {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewartical,parent,false)
        return help(view)
    }

    override fun onBindViewHolder(holder: help, position: Int) {
        val layoutParams = LinearLayout.LayoutParams(0, 0)
       holder.title.text=mdataclass.get(position).title
        if(mdataclass.get(position).icon_url.equals("null"))
        {
            holder.image.layoutParams = layoutParams
        }
        else
        {
            Glide.with(fragment.requireContext()).load(mdataclass.get(position).icon_url)
                .into(holder.image)
        }
        holder.itemView.setOnClickListener{

            moveToFragment(mdataclass.get(position))
        }

    }

    override fun getItemCount(): Int {
        return mdataclass.size
    }
    private fun moveToFragment(get: UsedData)
    {

        val direction = DisplayArticalDirections.actionFirstFragmentToSecondFragment(get.title,get.selftext,get.icon_url)
        NavHostFragment.findNavController(fragment).navigate(direction)
    }
}