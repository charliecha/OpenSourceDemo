package com.charlie.rx

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

fun main() {
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