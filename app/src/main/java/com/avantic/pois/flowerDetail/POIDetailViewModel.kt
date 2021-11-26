
package com.avantic.pois.flowerDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avantic.pois.data.DataSource
import com.avantic.pois.data.POI


class POIDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a flower that corresponds to an id. */
    fun getPOIForId(id: Long) : POI? {
        return datasource.getPOIForId(id)
    }

    /* Queries datasource to remove a flower. */
    fun removePOI(poi: POI) {
        datasource.removePOI(poi)
    }
}

class POIDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(POIDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return POIDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}