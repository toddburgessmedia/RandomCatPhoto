package com.toddburgessmedia.randomcatkotlin.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [CatPhoto::class], version = 2)
abstract class CatPhotoDB : RoomDatabase() {

    abstract fun catPhotoDao() : CatPhotoDAO

    companion object {
        var INSTANCE : CatPhotoDB? = null

        fun getAppDatabase (context : Context) : CatPhotoDB? {
            if (INSTANCE == null) {
                synchronized(CatPhotoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CatPhotoDB::class.java, "myDB").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }

}