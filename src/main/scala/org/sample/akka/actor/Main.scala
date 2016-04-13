package org.sample.akka.actor

import akka.actor.ActorSystem
import akka.actor.Props


object Main {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("sample")
    val ref = actorSystem.actorOf(Props[MyActor])
    
    while (true) {
      ref ! "Hello World"
      Thread.sleep(1000)
    }
  }
}