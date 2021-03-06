package com.toddburgessmedia.randomcatkotlin.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CatPhotoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatPhoto(catPhoto: CatPhoto)

    @Query("SELECT * FROM CatPhoto ORDER BY id DESC LIMIT 1")
    fun getCatPhoto() : CatPhoto?

    @Query("SELECT * FROM CatPhoto ORDER BY id")
    fun getAllCatPhotos() : List<CatPhoto>

    @Query("SELECT * FROM CatPhoto ORDER BY id")
    fun getLiveAllCatPhotos() : LiveData<List<CatPhoto>>



}