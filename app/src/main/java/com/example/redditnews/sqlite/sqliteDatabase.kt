package com.example.redditnews.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqliteDatabase(context:Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,"news", factory,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE " + TABLE_name + " ( "+ dataID + " INTEGER PRIMARY KEY , " + title + " TEXT , " + url + " TEXT )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_name)
        onCreate(db)
    }

    fun addlink(title : String,url : String){
        val values = ContentValues()
        values.put("title", title)
        values.put("url", url)
        val db = this.writableDatabase
        db.insert(TABLE_name , null, values)
        db.close()
    }
    fun getlink(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + "news", null)

    }
    companion object
    {
        val dataID="dataID"
        val title="title"
        val url="url"
        val TABLE_name="news"
    }
}