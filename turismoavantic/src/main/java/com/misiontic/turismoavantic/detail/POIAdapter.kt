package com.misiontic.turismoavantic.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misiontic.turismoavantic.R
import com.misiontic.turismoavantic.data.POI

class POIAdapter(private val onClick: (POI) -> Unit) :
    ListAdapter<POI, POIAdapter.POIViewHolder>(POIDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class POIViewHolder(itemView: View, val onClick: (POI) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val poiTextView: TextView = itemView.findViewById(R.id.flower_text)
        private val poiImageView: ImageView = itemView.findViewById(R.id.flower_image)
        private var currentPOI: POI? = null

        init {
            itemView.setOnClickListener {
                currentPOI?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(poi: POI) {
            currentPOI = poi

            poiTextView.text = poi.name
            if (poi.image != null) {
                poiImageView.setImageResource(poi.image)
            } else {
                poiImageView.setImageResource(R.drawable.poi1)
            }
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POIViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poi_item, parent, false)
        return POIViewHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: POIViewHolder, position: Int) {
        val POI = getItem(position)
        holder.bind(POI)

    }
}

object POIDiffCallback : DiffUtil.ItemCallback<POI>() {
    override fun areItemsTheSame(oldItem: POI, newItem: POI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: POI, newItem: POI): Boolean {
        return oldItem.id == newItem.id
    }
}