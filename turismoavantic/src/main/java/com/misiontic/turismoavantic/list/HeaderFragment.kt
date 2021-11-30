package com.misiontic.turismoavantic.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misiontic.turismoavantic.R
import com.misiontic.turismoavantic.databinding.FragmentHeaderBinding

class HeaderFragment : Fragment() {

    private lateinit var headerBinding: FragmentHeaderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        headerBinding = FragmentHeaderBinding.inflate(inflater, container, false)
        return headerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}