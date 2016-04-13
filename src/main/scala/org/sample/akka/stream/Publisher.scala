package org.sample.akka.stream

import akka.stream.actor.ActorPublisher
import akka.stream.actor.ActorPublisherMessage.Cancel
import akka.stream.actor.ActorPublisherMessage.Request


class Publisher extends ActorPublisher[Int] {
  import Publisher._
  
  val MaxBufferSize = 100
  var buffer = Vector.empty[Int]
  
  val isBufferFilled = buffer.size == MaxBufferSize
  
  def receive = {
    case i: Int =>
      println("received")
      if (isBufferFilled) {
        sender() ! ResponseDenied
      } else {
        sender() ! ResponseAccepted
        if (0 < totalDemand && buffer.isEmpty) {
          onNext(i)
        } else {
          buffer :+= i
          // deliverBuffer
        } 
      }
        
      
    case Request(_) =>
      println("request")

    case Cancel =>
      println("cancel")
        
  }
  
}

object Publisher {
  case object ResponseAccepted
  case object ResponseDenied
}