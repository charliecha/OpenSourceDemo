package org.charlie.network

import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    testOkHttp()
}

/**
 * 测试Okhttp框架
 */
fun testOkHttp() {
    val okHttpClient = OkHttpClient()
    val builder = Request.Builder()
    val url = "https://api.github.com/repos/square/retrofit/contributors"
    val request = builder.url(url).build()
    val response = okHttpClient.newCall(request).execute()
    println(response.body()?.string())
}
