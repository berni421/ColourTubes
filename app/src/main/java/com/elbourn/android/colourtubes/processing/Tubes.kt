package com.elbourn.android.colourtubes.processing

import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

class Tubes(val sketch: Sketch) {
    val TAG: String = javaClass.simpleName

    // List of tubes
    val tubes = arrayListOf<Tube>()

    // tubes in width and depth
    val numberTubesWidth = 12
    val numberTubesDepth = numberTubesWidth

    // tube sizes
    val tubesWidth = sketch.width / 1.9f
    val tubesDepth = tubesWidth
    val tubesBase = sketch.height * 0.1f
    val tubesMaxHeight = sketch.height * 0.8f
    val tubeWidth = tubesWidth / numberTubesWidth
    val tubeDepth = tubesDepth / numberTubesDepth

    init {
        for (x in 0 until tubesWidth.toInt() step tubeWidth.toInt()) {
            for (z in 0 until tubesDepth.toInt() step tubeDepth.toInt()) {
                // coordinates: center of tube
                val tube = Tube(
                    this,
                    sketch,
                    x.toFloat(),
                    0.toFloat(),
                    z.toFloat()
                )
                tubes.add(tube)
            }
        }
    }

    fun display() {
        sketch.push()
        sketch.translate(
            -tubesWidth / 2,
            tubesMaxHeight / 2 - tubesBase / 2,
            -tubesDepth / 2)
        for (tube in tubes.iterator()) {
            tube.display()
        }
        sketch.pop()
    }

    fun update() {
        for (tube in tubes.iterator()) {
            tube.update()
        }
    }
}

class Bands(val sketch: Sketch) {
    val bands = arrayListOf<Int>()
    val numberColourBands = 6

    init {
        var colourRed = sketch.random(255f)
        var colourGreen = sketch.random(255f)
        var colourBlue = sketch.random(255f)

        for (i in 0 until numberColourBands) {
            val alpha = sketch.random(255f)
            val newRed = colourRed + plusMinus() * sketch.random(128f)
            val newGreen = colourGreen + plusMinus() * sketch.random(128f)
            val newBlue = colourBlue + plusMinus() * sketch.random(128f)
            bands.add(
                sketch.color(
                    colourLimit(newRed),
                    colourLimit(newGreen),
                    colourLimit(newBlue),
                    alpha
                )
            )
            colourRed = newRed
            colourGreen = newGreen
            colourBlue = newBlue
        }
    }

    fun update() {
        for (i in 0 until bands.size) {
            val oldColour = bands[i]
            val newAlpha = oldColour.alpha + plusMinus() * sketch.random(32f)
            bands[i] = sketch.color(
                oldColour.red,
                oldColour.green,
                oldColour.blue,
                colourLimit(newAlpha).toInt()
            )
        }
    }

    fun plusMinus():Float {
        val v = sketch.random(1f)
        if (v < 0.5) return -1f
        return 1f
    }

    fun colourLimit(c:Float):Float {
        if (c < 0) return 0f
        if (c > 255) return 255f
        return c
    }
}

class Tube(
    val tubes: Tubes,
    val sketch: Sketch,
    var x: Float,
    var y: Float,
    var z: Float
) {
    val TAG: String = javaClass.simpleName

    // Tube details
    val height = sketch.random(tubes.tubesMaxHeight)
    val colourBands = Bands(sketch)

    fun display() {
        sketch.push()
//        Log.i(TAG, "x,y,z,height: $x, $y, $z, $height")
        val bandLength = height/colourBands.numberColourBands
        sketch.translate(x, -bandLength / 2f, z)
        for (b in 0 until colourBands.numberColourBands) {
//            Log.i(TAG, "b: " + b)
            sketch.fill(colourBands.bands[b])
//            sketch.noFill()
//            sketch.stroke(255)
//            sketch.strokeWeight(2f)
            sketch.noStroke()
            // box(w, h, d)
            sketch.box(tubes.tubeWidth, -bandLength, tubes.tubeDepth)
            sketch.translate(0f, -bandLength, 0f)
        }
        sketch.pop()
    }

    fun update() {
        colourBands.update()
    }
}






