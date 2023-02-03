package com.elbourn.android.colourtubes.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.elbourn.android.colourtubes.BuildConfig
import com.elbourn.android.colourtubes.R


class DisclaimerFragment : Fragment() {
    val TAG:String = javaClass.simpleName
    var APP: String = BuildConfig.APPLICATION_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disclaimer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "start onViewCreated")
//        val context = context
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(APP, Context.MODE_PRIVATE)
        val disclaimerCheckBox: Boolean = sharedPreferences.getBoolean("disclaimerCheckBox", false)
        Log.i(TAG, "disclaimerCheckBox: $disclaimerCheckBox")
        if (disclaimerCheckBox) {
            startIntroFragment()
        } else {
            setupButtons(view)
        }
        Log.i(TAG, "end onViewCreated")
    }

    fun setupButtons(view: View) {
        val disclaimerAgreedBox: CheckBox = view.findViewById<CheckBox>(R.id.disclaimerCheckBox)
        disclaimerAgreedBox.setOnClickListener(View.OnClickListener { v ->
            Log.i(TAG, "disclaimerAgreedBox clicked")
            val checkBox: CheckBox = v as CheckBox
//            val context = context
            val sharedPreferences: SharedPreferences =
                requireContext().getSharedPreferences(APP, Context.MODE_PRIVATE)
            if (checkBox.isChecked) {
                sharedPreferences.edit().putBoolean("disclaimerCheckBox", true).apply()
                startIntroFragment()
            }
            Log.i(TAG, "disclaimerAgreedBox: $disclaimerAgreedBox")
        })
    }

    fun startIntroFragment() {
        Log.i(TAG, "start startIntroFragment")
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_disclaimerFragment_to_introFragment)
        Log.i(TAG, "end startIntroFragment")
    }
}