package org.charlie.kotlin
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main() {
    // 开始执行主协程
    // 使用协程
//    println("Coroutines: start")
//    val jobs = List(100_000) {
//        // 创建新的coroutine
//        GlobalScope.launch {
//            // 挂起当前上下文而非阻塞1000ms
//            delay(1000L)
//            println("." + Thread.currentThread().name)
//        }
//    }
//    jobs.forEach { it.join() }
//    println("Coroutines: end")

    val noCoroutinesPool: ExecutorService = Executors.newCachedThreadPool()
    println("No Coroutines: start")
// 使用阻塞
    val noCoroutinesJobs = List(100_000) {
        Executors.callable {
            Thread.sleep(1000L)
            println("thread." + Thread.currentThread().name)
        }
    }

    noCoroutinesPool.invokeAll(noCoroutinesJobs)
    println("No Coroutines: end")
}