package com.elbourn.android.colourtubes.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elbourn.android.colourtubes.R
import com.elbourn.android.colourtubes.processing.Sketch
import processing.android.PFragment

class ProcessingFragment : Fragment() {
    val TAG: String = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_processing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "started onResume")
        processingFragment.setView(view.findViewById(R.id.frameLayout01), activity)
    }


    override fun onResume() {
        super.onResume()
        sketch = Sketch()
        processingFragment = PFragment(sketch)
    }

    companion object {
        var sketch = Sketch()
        var processingFragment = PFragment(sketch)
    }
}
