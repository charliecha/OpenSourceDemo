package org.charlie.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun main() {
    testRetrofit()
}


/**
 * 测试Retrofit框架
 */
fun testRetrofit() {
    val API_URL = "https://api.github.com"
    val retrofit = Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val gitHub = retrofit.create<GitHub>()
    val call = gitHub.contributors("square", "retrofit")
    val contributors = call.execute().body();
    contributors?.forEach {
        println("${it.login} ${it.contributions}")
    }
}