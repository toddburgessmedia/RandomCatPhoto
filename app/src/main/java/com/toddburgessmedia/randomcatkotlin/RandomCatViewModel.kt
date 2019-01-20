package com.toddburgessmedia.randomcatkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.toddburgessmedia.randomcatkotlin.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RandomCatViewModel(application: Application) : AndroidViewModel(application)  {

    var fileName : String? = ""
    var changeNotifier : MutableLiveData<String> = MutableLiveData()

    var appDB : CatPhotoDB? = null
    var catPhotoDAO : CatPhotoDAO? = null

    init {
        appDB = CatPhotoDB.getAppDatabase(application.baseContext)
        catPhotoDAO = appDB?.catPhotoDao()
    }

    fun getCatPhoto() : MutableLiveData<String> {

        return changeNotifier
    }

    fun loadCatPhotos() {

        val photoFactory = CatPhotoFactory.makeRetrofitService()

        GlobalScope.launch(Dispatchers.Main) {
            val request = photoFactory.getPhoto().await()
            val response = request
            fileName = response.body()!!.file
            changeNotifier.value = fileName

            GlobalScope.launch(Dispatchers.IO) {
                val catPhoto = CatPhoto(file = fileName)
                catPhotoDAO?.insertCatPhoto(catPhoto)
            }
        }
    }
}