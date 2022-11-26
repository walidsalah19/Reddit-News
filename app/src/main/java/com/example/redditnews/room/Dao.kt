package com.example.redditnews.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Query("select * from news")
    fun getlist():List<entity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertNews(entity: entity)


}