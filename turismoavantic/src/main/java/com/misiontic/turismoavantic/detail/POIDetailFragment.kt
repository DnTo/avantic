package com.misiontic.turismoavantic.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.misiontic.turismoavantic.R
import com.misiontic.turismoavantic.databinding.FragmentDetailBinding
import com.misiontic.turismoavantic.main.POI_ID

class POIDetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailBinding =  FragmentDetailBinding.inflate(inflater, container, false)

        return detailBinding.root
    }

    private val POIDetailViewModel by viewModels<POIDetailFragment> {
        POIDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

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