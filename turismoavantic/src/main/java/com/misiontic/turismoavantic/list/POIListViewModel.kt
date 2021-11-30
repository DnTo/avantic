package com.misiontic.turismoavantic.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misiontic.turismoavantic.data.DataSource
import com.misiontic.turismoavantic.data.POI
import kotlin.random.Random

class POIListViewModel(val dataSource: DataSource) : ViewModel() {

    val flowersLiveData = dataSource.getPOIList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertFlower(flowerName: String?, flowerDescription: String?,puntuacion:Float) {
        if (flowerName == null || flowerDescription == null) {
            return
        }

        val image = dataSource.getRandomPOIImageAsset()
        val newFlower = POI(
            Random.nextLong(),
            flowerName,
            image,
            flowerDescription,
            puntuacion
        )

        dataSource.addFlower(newFlower)
    }
}

class POIListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(POIListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return POIListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}