

package com.avantic.pois.flowerList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avantic.pois.addFlower.AddPOIActivity
import com.avantic.pois.flowerDetail.POIDetailActivity
import com.avantic.pois.R
import com.avantic.pois.addFlower.POI_DESCRIPTION
import com.avantic.pois.addFlower.POI_NAME
import com.avantic.pois.data.POI

const val POI_ID = "flower id"

class FlowersListActivity : AppCompatActivity() {
    private val newFlowerActivityRequestCode = 1
    private val flowersListViewModel by viewModels<FlowersListViewModel> {
        FlowersListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val flowersAdapter = POIAdapter { flower -> adapterOnClick(flower) }
        val concatAdapter = ConcatAdapter(headerAdapter, flowersAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                flowersAdapter.submitList(it as MutableList<POI>)
                headerAdapter.updatePOICount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
           // fabOnClick()
        }
    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(flower: POI) {
        val intent = Intent(this, POIDetailActivity()::class.java)
        intent.putExtra(POI_ID, flower.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddPOIActivity::class.java)
        startActivityForResult(intent, newFlowerActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts flower into viewModel. */
        if (requestCode == newFlowerActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val flowerName = data.getStringExtra(POI_NAME)
                val flowerDescription = data.getStringExtra(POI_DESCRIPTION)
               // var rate = data.getStringExtra(POI_RATE)

              //  flowersListViewModel.insertFlower(flowerName, flowerDescription,)
            }
        }
    }
}