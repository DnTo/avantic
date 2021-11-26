

package com.avantic.pois.addFlower

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avantic.pois.R
import com.google.android.material.textfield.TextInputEditText

const val POI_NAME = "name"
const val POI_DESCRIPTION = "description"

class AddPOIActivity : AppCompatActivity() {
    private lateinit var addPOIName: TextInputEditText
    private lateinit var addPOIDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_poi_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addPOI()
        }
        addPOIName = findViewById(R.id.add_poi_name)
        addPOIDescription = findViewById(R.id.add_poi_description)
    }

    /* The onClick action for the done button. Closes the activity and returns the new flower name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addPOI() {
        val resultIntent = Intent()

        if (addPOIName.text.isNullOrEmpty() || addPOIDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addPOIName.text.toString()
            val description = addPOIDescription.text.toString()
            resultIntent.putExtra(POI_NAME, name)
            resultIntent.putExtra(POI_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}