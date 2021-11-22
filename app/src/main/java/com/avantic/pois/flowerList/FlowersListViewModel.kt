

package com.avantic.pois.flowerList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avantic.pois.data.DataSource
import com.avantic.pois.data.Flower
import kotlin.random.Random

class FlowersListViewModel(val dataSource: DataSource) : ViewModel() {

    val flowersLiveData = dataSource.getFlowerList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertFlower(flowerName: String?, flowerDescription: String?,puntuacion:Float) {
        if (flowerName == null || flowerDescription == null) {
            return
        }

        val image = dataSource.getRandomFlowerImageAsset()
        val newFlower = Flower(
            Random.nextLong(),
            flowerName,
            image,
            flowerDescription,
            puntuacion
        )

        dataSource.addFlower(newFlower)
    }
}

class FlowersListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlowersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FlowersListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}