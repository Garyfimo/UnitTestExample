package com.garyfimo.unittestexample

import androidx.test.rule.ActivityTestRule
import com.garyfimo.unittestexample.util.ScreenshotWatcher
import com.garyfimo.unittestexample.util.checkDisplayed
import com.garyfimo.unittestexample.util.viewWithId
import com.garyfimo.unittestexample.util.viewWithText
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

internal class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var testName = TestName()

    @get:Rule
    var screenshotWatcher = ScreenshotWatcher()

    private val tv = viewWithId(R.id.tv)

    @Test
    fun verficiarQueTextoSeaHelloWorld() {
        viewWithText("Hello World!").checkDisplayed()
    }
}