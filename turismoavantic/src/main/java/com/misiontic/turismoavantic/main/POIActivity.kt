package com.misiontic.turismoavantic.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misiontic.turismoavantic.R
import com.misiontic.turismoavantic.data.POI
import com.misiontic.turismoavantic.detail.POIDetailFragment
import com.misiontic.turismoavantic.detail.POIListViewModel
import com.misiontic.turismoavantic.detail.POIListViewModelFactory
import com.misiontic.turismoavantic.list.HeaderFragment
import com.misiontic.turismoavantic.list.POIAdapter

const val POI_ID = "flower id"

class POIActivity : AppCompatActivity() {
    private val newPOIActivityRequestCode = 1
    private val poiListViewModel by viewModels<POIListViewModel> {
        POIListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detailFragmentPOI = POIDetailFragment()
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.fragment_detail)

        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderFragment()
        val poiAdapter = POIAdapter { flower -> adapterOnClick(flower) }
        val concatAdapter = ConcatAdapter(headerAdapter, poiAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        poiListViewModel.flowersLiveData.observe(this, {
            it?.let {
                poiAdapter.submitList(it as MutableList<POI>)
                headerAdapter.updatePOICount(it.size)
            }
        })

    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(flower: POI) {
        val intent = Intent(this, POIDetailFragment()::class.java)
        intent.putExtra(POI_ID, flower.id)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts flower into viewModel. */
        if (requestCode == newPOIActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val flowerName = data.getStringExtra(POI_NAME)
                val flowerDescription = data.getStringExtra(POI_DESCRIPTION)
                // var rate = data.getStringExtra(POI_RATE)

                //  flowersListViewModel.insertFlower(flowerName, flowerDescription,)
            }
        }
    }
}