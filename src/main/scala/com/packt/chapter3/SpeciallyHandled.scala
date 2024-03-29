package com.packt.chapter3

import akka.actor.{PoisonPill, Props, ActorSystem, Actor}
import akka.routing.{Broadcast, RandomPool}

case object Handle

class SpeciallyHandledActor extends Actor {
  override def receive = {
    case Handle => println(s"${self.path.name} says hello")
  }
}

object SpeciallyHandled extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val router =
    actorSystem.actorOf(RandomPool(5).props(Props[SpeciallyHandledActor]))
  router ! Broadcast(Handle)
  router ! Broadcast(PoisonPill)
  router ! Handle
}
