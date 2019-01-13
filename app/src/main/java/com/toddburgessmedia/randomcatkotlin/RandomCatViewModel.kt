package com.toddburgessmedia.randomcatkotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.toddburgessmedia.randomcatkotlin.model.CatPhotoFactory
import com.toddburgessmedia.randomcatkotlin.model.CatPhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RandomCatViewModel : ViewModel()  {

    var fileName : String? = ""
    var changeNotifier : MutableLiveData<String> = MutableLiveData()

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
        }
    }
}