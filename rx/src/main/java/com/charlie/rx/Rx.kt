package com.charlie.rx

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit
import kotlin.random.Random

val intObservable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
    if (!emitter.isDisposed) {
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onNext(4)
        emitter.onComplete()
    }
})

val intObserver = object : Observer<Int> {
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

val stringObservable = Observable.create(ObservableOnSubscribe<String> { emitter ->
    if (!emitter.isDisposed) {
        emitter.onNext("a")
        emitter.onNext("b")
        emitter.onNext("c")
        emitter.onNext("d")
        emitter.onNext("e")
        emitter.onComplete()
    }
})

val stringObserver = object : Observer<String> {
    override fun onNext(t: String) {
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

fun main() {
//    testObservable()
//    testFlowable()
//    testMap()
//    testZip()
//    testBuffer()
//    testTimer()
//    testInterval()
//    testDoOnNext()
//    testSkip()
//    testTake()
//    testJust()
    testSinge()
//    testDistinct()
}

private fun testDistinct() {
    Observable.just(6, 5, 4, 2, 2, 6).distinct().subscribe(intObserver)
}

private fun testSinge() {
    Single.just(Random.nextInt()).subscribe(object : SingleObserver<Int> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onSuccess(t: Int) {
            println("onSuccess $t")
        }

        override fun onError(e: Throwable) {
            println("onError $e")
        }
    })
}

private fun testJust() {
    Observable.just(6, 5, 4, 3, 2, 1).subscribe(intObserver)
}

private fun testTake() {
    intObservable.take(3).subscribe(intObserver)
}

private fun testSkip() {
    intObservable.skip(3).subscribe(intObserver)
}


private fun testDoOnNext() {
    intObservable.doOnNext {
        println(Thread.currentThread().name)
    }.subscribeOn(Schedulers.computation()).observeOn(Schedulers.io()).subscribe(intObserver)
    Thread.sleep(1000)
}

private fun testInterval() {
    Observable.interval(1, TimeUnit.SECONDS).subscribe { println(it) }
    Thread.sleep(6000)
}

private fun testTimer() {
    Observable.timer(3, TimeUnit.SECONDS).subscribe { println(it) }
    Thread.sleep(6000)
}

private fun testBuffer() {
    Observable.just(1, 2, 3, 4, 5, 6).buffer(3, 2).subscribe {
        println(it)
    }
}


private fun testZip() {
    Observable.zip(intObservable, stringObservable, BiFunction<Int, String, String>() { t1, t2 ->
        "" + t1 + "_" + t2
    }).subscribe(stringObserver)
}

private fun testMap() {
    intObservable.subscribe(intObserver)
    intObservable.map { 2 * it }.subscribe(intObserver)
}

private fun testObservable() {
    Observable.just("Hello world").subscribe { word ->
        println("got $word @${Thread.currentThread().name}")
    }

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

    intObservable.subscribe(observer)
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