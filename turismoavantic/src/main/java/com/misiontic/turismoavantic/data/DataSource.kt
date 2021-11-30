package com.misiontic.turismoavantic.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataSource(resources: Resources) {
    private val initialPOIList = listOf(POI(0, "", 0, "", 0.0f))

    //flowerList(resources)
    private val poiLiveData = MutableLiveData(initialPOIList)

    private val context = resources

    /* Adds flower to liveData and posts value. */
    fun addFlower(poi: POI) {
        val currentList = poiLiveData.value
        if (currentList == null) {
            poiLiveData.postValue(listOf(poi))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, poi)
            poiLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removePOI(poi: POI) {
        val currentList = poiLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(poi)
            poiLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getPOIForId(id: Long): POI? {
        poiLiveData.value?.let { flowers ->
            return flowers.firstOrNull { it.id == id }
        }
        return null
    }

    fun getPOIList(): LiveData<List<POI>> {
        //test create GSON
        val gson = Gson()

        val json = loadDataFromJson("pois.json")
        val fooType: Type = object : TypeToken<List<POI?>?>() {}.type
        val objects = gson.fromJson<List<POI>>(json, fooType)
        poiLiveData.value = objects

        return poiLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomPOIImageAsset(): Int? {
        val randomNumber = (initialPOIList.indices).random()
        return initialPOIList[randomNumber].image
    }


    fun loadDataFromJson(fileName: String): String {


        val json = context.assets.open(fileName)
            .readBytes()
            .toString(Charsets.UTF_8)
        return json
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}