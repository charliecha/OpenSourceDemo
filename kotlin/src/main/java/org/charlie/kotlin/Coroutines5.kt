package org.charlie.kotlin
import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main(args: Array<String>) = runBlocking<Unit> {
    val job = GlobalScope.launch() {
        // 挂起1000ms
        println("4")
        delay(1000L)
        println("3")
        println(Thread.currentThread().name)
    }
    // 接口含义同Thread.join只是这里是`suspension`
    println("1")
    job.join()
    println("2")
    println(Thread.currentThread().name)
}

// 编译失败案例
fun noRunBlocking(args: Array<String>) {
    val job = GlobalScope.launch() {
        delay(1000L)
    }
    // 这里会报Suspend function 'join' should be called only from a coroutine or another suspend function
//    job.join()
}