package com.elbourn.android.colourtubes.processing

import android.util.Log
import processing.core.PApplet

class Sketch : PApplet() {
    val TAG: String = javaClass.simpleName
    val permissions: Array<String>? =
        null // or {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_CALENDAR};
    lateinit var tubes:Tubes

    override fun settings() {
        size(displayWidth, displayHeight, P3D)
    }

    override fun setup() {
        Log.i(TAG, "start setup")
        // Check permissions
        requestPermissions(permissions)
        Log.i(TAG, "width: " + width)
        // Build tubes
        tubes = Tubes(this)
        background(0)
        frameRate(10f)
        Log.i(TAG, "end setup")
    }

    override fun draw() {
//        Log.i(TAG, "started draw")
        // Check permissions OK
        if (!hasPermissions(permissions)) return
        // Display current tubes state
        background(0)
        translate(width/2f, height/2f, 0f)
        rotateY(0.1f * frameCount / TWO_PI)
        tubes.display()
//        noLoop()
    }

    private fun requestPermissions(permissions: Array<String>?) {
        if (permissions == null) return
        for (permission in permissions) {
            requestPermission(permission)
        }
    }

    private fun hasPermissions(permissions: Array<String>?): Boolean {
        if (permissions == null) return true
        var t = ""
        for (permission in permissions) {
            if (!hasPermission(permission)) {
                val p = permission.substring(permission.lastIndexOf('.') + 1)
                t = t + p + "\n"
            }
        }
        text(t + "permission required", (width / 2).toFloat(), (height / 2).toFloat())
        return t == ""
    }

    override fun touchStarted() {
    }

    override fun touchMoved() {
    }

    override fun touchEnded() {
    }
}