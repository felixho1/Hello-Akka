package com.packt.chapter4

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object AddFuture extends App {
  val future = Future(1 + 2).mapTo[Int]
  println("before await")
  val sum = Await.result(future, 10 seconds)
  println(s"Future Result $sum")
}
