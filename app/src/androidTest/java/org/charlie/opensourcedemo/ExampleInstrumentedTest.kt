package org.charlie.opensourcedemo

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("org.charlie.opensourcedemo", appContext.packageName)
        testReq(appContext, "https://www.baidu.com")
    }

    fun testReq(context : Context, url : String) {
        Volley.newRequestQueue(context).add(StringRequest(url,
            Response.Listener<String>() { response ->
                {
                    Log.i("tag", response.toString())
                }
            }, Response.ErrorListener { error ->
                {
                    Log.i("tag", error.toString())
                }
            }
        ))
    }
}
