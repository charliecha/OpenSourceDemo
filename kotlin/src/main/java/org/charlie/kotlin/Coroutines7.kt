package org.charlie.kotlin
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

// 我们在主线程调用了main方法
fun main(args: Array<String>) = runBlocking<Unit> {
    val jobs = arrayListOf<Job>()
    jobs += launch() {
        println("      'Unconfined': I'm working in thread ${Thread.currentThread().name}") // 这里将在主线程访问
        delay(500)
        println("      'Unconfined': After delay in thread ${Thread.currentThread().name}") // 这里将在DefaultExecutor中被访问
    }
    jobs += launch(coroutineContext) { // 父coroutine的coroutineContext, runBlocking的coroutine，因此始终在主线程
        println("'coroutineContext': I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("'coroutineContext': After delay in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }
}