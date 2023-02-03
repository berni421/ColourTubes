package com.elbourn.android.colourtubes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.elbourn.android.colourtubes.fragments.ProcessingFragment
import com.elbourn.android.colourtubes.fragments.ProcessingFragment.Companion.processingFragment


class MainActivity : OptionsMenu() {
    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        sketch.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        sketch.onNewIntent(intent)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    companion object {
        var sketch = ProcessingFragment.sketch
    }
}