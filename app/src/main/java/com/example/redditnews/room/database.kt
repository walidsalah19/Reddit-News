package com.example.redditnews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [entity::class], version = 1)
abstract class database : RoomDatabase() {
    abstract fun mDao(): Dao
    companion object
       {

           @Synchronized
           fun getInstance(mCtx: Context): database {
                 var   mdatabase = Room.databaseBuilder(mCtx.applicationContext,database::class.java,"news")
                       .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                       .build()
               return mdatabase
           }
       }
}