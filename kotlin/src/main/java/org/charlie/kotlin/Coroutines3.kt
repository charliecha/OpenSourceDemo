package org.charlie.kotlin

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> { // 开始执行主协程
    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待直到子协程执行结束
}