package com.accenture.codingexercise.view

import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Instrumentation test case for MainActivity
 */
class MainActivityTest {

   var mMainActivity: MainActivity? = null

    @get:Rule
    var mActivityRule:ActivityTestRule<MainActivity> =  ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        mMainActivity = mActivityRule.activity
    }

    @Test
    fun testGetDataFromViewModel() {
        var method: Method? = null
        try {
            method = mMainActivity!!.javaClass.getDeclaredMethod("getDataFromViewModel")
            method.isAccessible = true
            method.invoke(mMainActivity)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    @After
    fun tearDown() {
        mMainActivity = null
    }
}