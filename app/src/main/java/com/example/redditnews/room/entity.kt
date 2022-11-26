package com.example.redditnews.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "news")
class entity(  @PrimaryKey(autoGenerate = true) var id:Int?=null
               ,@ColumnInfo(name = "title") var title:String
               , @ColumnInfo(name = "selftext")var selftext:String
               ,@ColumnInfo(name = "icon_url") var icon_url:String) {


}