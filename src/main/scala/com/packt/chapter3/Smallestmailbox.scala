package com.packt.chapter3
import akka.actor.{Props, ActorSystem, Actor}
import akka.routing.SmallestMailboxPool

class SmallestmailboxActor extends Actor {
  override def receive = {
    case msg: String => println(s" I am ${self.path.name}")
    case _           => println(s" I don't understand the message")
  }
}

object Smallestmailbox extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val router =
    actorSystem.actorOf(
      SmallestMailboxPool(5).props(Props[SmallestmailboxActor])
    )
  for (i <- 1 to 5) {
    router ! s"Hello $i"
  }
}
