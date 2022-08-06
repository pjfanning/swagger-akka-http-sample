package com.example.akka.addoption

import akka.actor.Actor
import io.swagger.v3.oas.annotations.media.Schema

object AddOptionActor {
  // due to type erasure of inner types for scala.Option[T] when T is a primitive type like Int,
  // the only way to ensure generated swagger doc has int type for number2 is to use an annotation
  // if you upgrade to swagger-scala-module v2.7.0, the Schema annotation can be removed (but is still needed for Scala 3 users)
  case class AddOptionRequest(number: Int,
                              @Schema(required = false, implementation = classOf[Int]) number2: Option[Int] = None)
  //case class AddOptionRequest(number: Int, number2: Option[Int] = None)
  case class AddOptionResponse(sum: Int)
}

class AddOptionActor extends Actor {
  import AddOptionActor._

  def receive: Receive = {
    case request: AddOptionRequest =>
      sender ! AddOptionResponse(request.number + request.number2.getOrElse(0))
  }
}