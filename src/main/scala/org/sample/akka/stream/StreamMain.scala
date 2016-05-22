package org.sample.akka.stream

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Flow
import akka.stream.scaladsl.FlowGraph
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import akka.stream.scaladsl.Broadcast
import akka.stream.scaladsl.Merge

object StreamMain {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorFlowMaterializer()

    val source = Source[Int](1 to 5)
    val sink = Sink.foreach { x: Int => println(x) }

    val step1 = Flow[Int].map(_ * 2)
    val step2 = Flow[Int].map(_ * 10)

    val broadcast = Broadcast[Int]
    val merge = Merge[Int]

    val graph = FlowGraph {implicit builder =>
      import akka.stream.scaladsl.FlowGraphImplicits._
      source ~> broadcast ~> step1 ~> merge ~> sink
                broadcast ~> step2 ~> merge
    }

    graph.runWith(sink)
  }
}