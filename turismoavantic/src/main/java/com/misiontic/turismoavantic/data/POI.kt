package com.misiontic.turismoavantic.data

import androidx.annotation.DrawableRes

data class POI(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    val description: String,
    val rate:Float
)