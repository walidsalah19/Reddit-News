package com.example.redditnews.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "news")
class Entity(@ColumnInfo(name = "title") var title:String
             , @ColumnInfo(name = "selftext")var selftext:String
             , @ColumnInfo(name = "icon_url") var icon_url:String)
{
    @PrimaryKey(
        autoGenerate = true
    )
    var id : Int ?=null
}