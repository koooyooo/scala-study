package org.sample.actor

import akka.actor.Actor

class MyActor2 extends Actor {
  def receive: Receive = {
    case msg => println("actor2 receives:" + msg)
  }
}