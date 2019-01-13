package com.toddburgessmedia.randomcatkotlin.model

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CatPhotoService {

    @GET("/meow")
    fun getPhoto() : Deferred<Response<FileURL>>

}