package org.sample.akka.actor

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala

class MyActor extends Actor {

  val actorSystem = ActorSystem("sample")
  val ref2 = actorSystem.actorOf(Props[MyActor2])
  
  def receive: Receive = {
    case msg => 
      println("actor1 receives:" + msg)    
      ref2 ! "Good Night World"
  }
}