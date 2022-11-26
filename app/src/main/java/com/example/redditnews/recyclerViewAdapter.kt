package com.example.redditnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redditnews.dataModels.dataclass

class recyclerViewAdapter (var mdataclass:dataclass,var activity:MainActivity) : RecyclerView.Adapter<recyclerViewAdapter.help>() {
    var imagePosition=0
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
       holder.title.text=mdataclass.data.children.get(position).data.title
        if(mdataclass.data.children.get(position).data.all_awardings.isEmpty())
        {
            holder.image.layoutParams = layoutParams
        }
        else
        {
            Glide.with(activity).load(mdataclass.data.children.get(position).data.all_awardings.get(imagePosition).icon_url)
                .into(holder.image)
        }

    }

    override fun getItemCount(): Int {
        return mdataclass.data.children.size
    }
}