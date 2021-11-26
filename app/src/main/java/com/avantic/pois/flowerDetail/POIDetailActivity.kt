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

package com.avantic.pois.flowerDetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.avantic.pois.R
import com.avantic.pois.flowerList.POI_ID

class POIDetailActivity : AppCompatActivity() {

    private val POIDetailViewModel by viewModels<POIDetailViewModel> {
        POIDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poi_detail_activity)


        var currentPOIId: Long? = null

        /* Connect variables to UI elements. */
        val poiName: TextView = findViewById(R.id.poi_detail_name)
        val poiImage: ImageView = findViewById(R.id.poi_detail_image)
        val poiDescription: TextView = findViewById(R.id.poi_detail_description)
        val poiRate: TextView = findViewById(R.id.poi_rate)
        //val removeFlowerButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentPOIId = bundle.getLong(POI_ID)
        }

        /* If currentFlowerId is not null, get corresponding flower and set name, image and
        description */
        currentPOIId?.let {
            val currentPOI = POIDetailViewModel.getPOIForId(it)
            poiName.text = currentPOI?.name
            if (currentPOI?.image == null) {
                poiImage.setImageResource(R.drawable.poi1)
            } else {
                poiImage.setImageResource(currentPOI.image)
              //  flowerImage.seti
            }
            poiDescription.text = currentPOI?.description
            poiRate.text = currentPOI?.rate.toString()

//            removeFlowerButton.setOnClickListener {
//                if (currentFlower != null) {
//                    flowerDetailViewModel.removeFlower(currentFlower)
//                }
//                finish()
//            }
        }

    }
}