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

    lateinit var appDB : CatPhotoDB
    lateinit var catPhotoDAO : CatPhotoDAO

    init {
        appDB = CatPhotoDB.getAppDatabase(application.baseContext)
        catPhotoDAO = appDB.catPhotoDao()
    }

    fun getCatPhoto() : MutableLiveData<String> {

        return changeNotifier
    }

    fun getAllCatPhotos() {

        GlobalScope.launch(Dispatchers.IO) {
            val catPhotos = catPhotoDAO.getAllCatPhotos()
            dbChangeNotifier.postValue(catPhotos)
        }

    }

    fun startModelView() {

        GlobalScope.launch(Dispatchers.IO) {
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

        GlobalScope.launch(Dispatchers.IO) {
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