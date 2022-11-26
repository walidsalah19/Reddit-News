package com.example.redditnews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Entity::class], version = 1)
abstract class DatabaseClass : RoomDatabase() {
    abstract fun mDao(): Dao
    companion object
       {

           @Synchronized
           fun getInstance(mCtx: Context): DatabaseClass {
                 var   mdatabase = Room.databaseBuilder(mCtx.applicationContext,DatabaseClass::class.java,"news")
                       .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                       .build()
               return mdatabase
           }
       }
}