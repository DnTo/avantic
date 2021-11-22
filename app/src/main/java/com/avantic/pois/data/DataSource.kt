/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.avantic.pois.data

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.io.File
import java.io.InputStream
import 	android.content.ContextWrapper
import android.content.res.AssetManager
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/* Handles operations on flowersLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialFlowerList = listOf(Flower(0,"",0,"",0.0f))
        //flowerList(resources)
    private val flowersLiveData = MutableLiveData(initialFlowerList)

    private val context = resources

    /* Adds flower to liveData and posts value. */
    fun addFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(listOf(flower))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removeFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getFlowerForId(id: Long): Flower? {
        flowersLiveData.value?.let { flowers ->
            return flowers.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getFlowerList(): LiveData<List<Flower>> {
        //test create GSON
        var gson = Gson()

        var json = loadDataFromJson("pois.json")
        val fooType: Type = object : TypeToken<List<Flower?>?>() {}.type
        var objects = gson.fromJson<List<Flower>>(json,fooType)
        flowersLiveData.value = objects

        return flowersLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomFlowerImageAsset(): Int? {
        val randomNumber = (initialFlowerList.indices).random()
        return initialFlowerList[randomNumber].image
    }


    fun loadDataFromJson(fileName:String ) :String{


        var json = context.assets.open(fileName)
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