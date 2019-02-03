package com.toddburgessmedia.randomcatkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.toddburgessmedia.randomcatkotlin.model.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RandomCatViewModel(application: Application) : AndroidViewModel(application), CoroutineScope  {

    var fileName : String? = ""
    var changeNotifier : MutableLiveData<String> = MutableLiveData()

    var dbChangeNotifier = MutableLiveData<List<CatPhoto>>()

    var appDB : CatPhotoDB
    var catPhotoDAO : CatPhotoDAO

    init {
        appDB = CatPhotoDB.getAppDatabase(application.baseContext)
        catPhotoDAO = appDB.catPhotoDao()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun getCatPhoto() : MutableLiveData<String> {

        return changeNotifier
    }

    fun getAllCatPhotos() {

        this.launch {
            val catPhotos = catPhotoDAO.getAllCatPhotos()
            dbChangeNotifier.postValue(catPhotos)
        }

    }

    fun startModelView() {

        this.launch {
            val catPhoto = catPhotoDAO.getCatPhoto()
            if (catPhoto?.file != null) {
                with(catPhoto) {
                    changeNotifier.postValue(file)
                }
            } else {
                loadCatPhotos()
            }
        }
    }

    fun loadCatPhotos() {

        val photoFactory = CatPhotoFactory.makeRetrofitService()

        this.launch {
            val request = photoFactory.getPhoto().await()
            val response = request
            fileName = response.body()?.file
            fileName?.let {
                changeNotifier.postValue(it)
                val catPhoto = CatPhoto(file = it)
                catPhotoDAO.insertCatPhoto(catPhoto)
            }
        }
    }
}