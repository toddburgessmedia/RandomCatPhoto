package com.toddburgessmedia.randomcatkotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class CatPhoto (

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    var file : String
)