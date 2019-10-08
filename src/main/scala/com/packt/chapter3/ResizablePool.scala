package com.packt.chapter3

import akka.actor.{Props, Actor, ActorSystem}
import akka.routing.{RoundRobinPool, DefaultResizer}

case object Load

class LoadActor extends Actor {
  def receive = {
    case Load => println("Handing loads of requests")
  }
}

object ResizablePool extends App {
  val system = ActorSystem("Hello-Akka")
  val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)
  val router =
    system.actorOf(RoundRobinPool(5, Some(resizer)).props(Props[LoadActor]))
  router ! Load
}
