package com.toddburgessmedia.randomcatkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.toddburgessmedia.randomcatkotlin.model.*
import kotlinx.coroutines.*

class RandomCatViewModel(application: Application) : AndroidViewModel(application)  {

    var fileName : String? = ""
    var changeNotifier : MutableLiveData<String> = MutableLiveData()

    var dbChangeNotifier = MutableLiveData<List<CatPhoto>>()

    var appDB : CatPhotoDB? = null
    var catPhotoDAO : CatPhotoDAO? = null

    init {
        appDB = CatPhotoDB.getAppDatabase(application.baseContext)
        catPhotoDAO = appDB?.catPhotoDao()
    }

    fun getCatPhoto() : MutableLiveData<String> {

        return changeNotifier
    }

    fun getAllCatPhotos() {

        GlobalScope.launch(Dispatchers.IO) {
            val catPhotos = catPhotoDAO?.getAllCatPhotos()
            withContext(Dispatchers.Main) {
                    dbChangeNotifier.value = catPhotos
            }
        }

    }

    fun startModelView() {

        GlobalScope.launch(Dispatchers.IO) {
            val catPhoto = catPhotoDAO?.getCatPhoto()
            if (catPhoto?.file != null) {
                with(catPhoto) {
                    withContext(Dispatchers.Main) {
                        changeNotifier.value = file
                    }
                }
            } else {
                loadCatPhotos()
            }
            val photos = catPhotoDAO?.getAllCatPhotos()
            photos?.forEach {
                Log.d("CATPHOTO", "photo: ${it.file}")
            }
        }
    }

    fun loadCatPhotos() {

        val photoFactory = CatPhotoFactory.makeRetrofitService()

        GlobalScope.launch(Dispatchers.Main) {
            val request = photoFactory.getPhoto().await()
            val response = request
            fileName = response.body()?.file
            changeNotifier.value = fileName

            GlobalScope.launch(Dispatchers.IO) {
                val catPhoto = CatPhoto(file = fileName!!)
                catPhotoDAO?.insertCatPhoto(catPhoto)
            }
        }
    }
}