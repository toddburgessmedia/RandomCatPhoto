package com.toddburgessmedia.randomcatkotlin.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CatPhotoFactory {

    const val URL = "https://aws.random.cat"

    fun makeRetrofitService() : CatPhotoService {

        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(CatPhotoService::class.java)
    }



}