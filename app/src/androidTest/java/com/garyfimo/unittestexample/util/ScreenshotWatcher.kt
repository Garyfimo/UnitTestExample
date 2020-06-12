package com.garyfimo.unittestexample.util

import android.graphics.Bitmap
import androidx.test.runner.screenshot.Screenshot
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.IOException

class ScreenshotWatcher : TestWatcher() {

    override fun failed(e: Throwable?, description: Description) {
        try {
            captureScreenshot(description.methodName)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun captureScreenshot(name: String) {
        val capture = Screenshot.capture()
        capture.format = Bitmap.CompressFormat.PNG
        capture.name = name
        capture.process()
    }
}