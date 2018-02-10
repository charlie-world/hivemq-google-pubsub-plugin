package com.charlieworld.hivemq.pubsub

import com.google.api.core.{ApiFuture, ApiFutureToListenableFuture}
import com.google.common.util.concurrent.{FutureCallback, Futures, ListenableFuture}

import scala.concurrent.{Future, Promise}

/**
  * Writer Charlie Lee 
  * Created at 2018. 2. 10.
  */
trait FutureConversion {

  /**
    * Google Guava ListenableFuture to Scala Future
    *
    * @param lf ListenableFuture[T]
    * @return Future of T
    * */
  implicit class ListenableFutureConverter[T](lf: ListenableFuture[T]) {
    def toScala: Future[T] = {
      val p = Promise[T]()
      Futures.addCallback(lf, new FutureCallback[T] {
        def onFailure(t: Throwable): Unit = p failure t
        def onSuccess(result: T): Unit    = p success result
      })
      p.future
    }
  }

  implicit class ApiFutureConverter[T](apiFuture: ApiFuture[T]) {
    def toScala: Future[T] = {
      val lf = new ApiFutureToListenableFuture(apiFuture)
      lf.toScala
    }
  }
}
