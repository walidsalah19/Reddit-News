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
           var mdatabase:database ?=null

           @Synchronized
           fun getInstance(mCtx: Context): database ?{
               if (mdatabase == null) {
                   mdatabase = Room.databaseBuilder(mCtx.applicationContext,database::class.java,"news")
                       .fallbackToDestructiveMigration()
                       .build()
               }
               return mdatabase
           }
       }
}