package com.charlie.rx

import io.reactivex.*
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

fun main() {
//    testObservable()
//    testFlowable()
    testOpt()
}

private fun testOpt() {
    val observable = Observable.create(ObservableOnSubscribe<Int>() { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onNext(4)
        emitter.onComplete()
    })

    val observer = object : Observer<Int> {
        override fun onNext(t: Int) {
            println("onNext $t")
        }

        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("onError $e")
        }
    }

    observable.map { 2 * it }.subscribe(observer)
}

private fun testObservable() {
    Observable.just("Hello world").subscribe { word ->
        println("got $word @${Thread.currentThread().name}")
    }

    val observable = Observable.create(ObservableOnSubscribe<Int>() { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onNext(4)
        emitter.onComplete()
    })

    val observer = object : Observer<Int> {
        override fun onNext(t: Int) {
            println("onNext $t")
        }

        override fun onComplete() {
            println("onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("onError $e")
        }
    }

    observable.subscribe(observer)
}

private fun testFlowable() {
    Flowable.range(0, 10).subscribe {
        println(it)
    }

    Flowable.range(0, 10).subscribe(object : Subscriber<Int> {
        var s: Subscription? = null
        override fun onNext(t: Int?) {
            println("onNext $t")
            s?.request(1)
        }

        override fun onError(t: Throwable?) {
            println("onNext $t")
        }

        override fun onSubscribe(s: Subscription) {
            println("onSubscribe")
            this.s = s
            s.request(1)
        }

        override fun onComplete() {
            println("onComplete")
        }
    })
}