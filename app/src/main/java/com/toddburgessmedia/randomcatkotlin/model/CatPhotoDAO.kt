package com.toddburgessmedia.randomcatkotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

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

    @Delete
    fun deletePhoto(catPhoto: CatPhoto)



}