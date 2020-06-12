package com.garyfimo.unittestexample.util

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Inline functions
 */
fun viewWithText(text: String): ViewInteraction = Espresso.onView(ViewMatchers.withText(text))

fun viewWithText(stringId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withText(stringId))

fun viewWithId(id: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(id))

/**
 * ViewActions
 */
fun ViewInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun ViewInteraction.type(text: String): ViewInteraction = perform(ViewActions.typeText(text))

fun ViewInteraction.replace(text: String): ViewInteraction = perform(ViewActions.replaceText(text))

fun ViewInteraction.closeKeyboard(): ViewInteraction = perform(ViewActions.closeSoftKeyboard())

/**
 * ViewInteraction extensions
 */

fun ViewInteraction.checkDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.checkNotDisplayed(): ViewInteraction =
    check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))

fun ViewInteraction.checkDoesNotExist(): ViewInteraction =
    check(ViewAssertions.doesNotExist())

fun ViewInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
    check(ViewAssertions.matches(matcher))

fun ViewInteraction.pressEspressoBack() = Espresso.pressBack()

/**
 * Aggregated matchers
 */
fun ViewInteraction.allOf(vararg matcher: Matcher<View>): ViewInteraction {
    return check(ViewAssertions.matches(Matchers.allOf(matcher.asIterable())))
}

/**
 * ViewMatchers
 */
fun parentWithId(id: Int): Matcher<View> = ViewMatchers.withParent(ViewMatchers.withId(id))