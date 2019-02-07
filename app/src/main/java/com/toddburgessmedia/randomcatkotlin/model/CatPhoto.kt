package com.toddburgessmedia.randomcatkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatPhoto (

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    var file : String
)