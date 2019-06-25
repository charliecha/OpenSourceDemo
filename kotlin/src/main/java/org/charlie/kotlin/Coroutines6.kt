package org.charlie.kotlin
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking<Unit> {
    // 计算总共需要执行多久，measureTimeMillis是kotlin标准库中所提供的方法
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doOne() } // 这里将doOne抛到CommonPool中的线程执行，并在结束时将结果带回来。
        val two = async(start = CoroutineStart.LAZY) { doTwo() } // 这里将doTwo抛到CommonPool中的线程执行，并在结束时将结果带回来。
        println("The answer is ${one.await() + two.await()}") // 这里会输出6
    }
    println("${time}ms") // 由于doOne与doTwo在异步执行，因此这里输出大概是700ms
}

suspend fun doOne() : Int {
    delay(500L)
    return 1
}
suspend fun doTwo() : Int {
    delay(700L)
    return 5
}