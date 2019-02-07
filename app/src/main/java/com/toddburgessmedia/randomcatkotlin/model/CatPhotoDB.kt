package com.toddburgessmedia.randomcatkotlin.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [CatPhoto::class], version = 2, exportSchema = false)
abstract class CatPhotoDB : RoomDatabase() {

    abstract fun catPhotoDao() : CatPhotoDAO

    companion object {
        private var INSTANCE : CatPhotoDB? = null

        fun getAppDatabase (context : Context) : CatPhotoDB {
            INSTANCE ?: run {
                synchronized(CatPhotoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CatPhotoDB::class.java, "myDB").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE ?: throw Exception("Unable to create db")
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }

}