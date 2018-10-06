package com.example.akka.addoption

import akka.actor.Actor
import io.swagger.v3.oas.annotations.media.Schema

object AddOptionActor {
  // due to type erasure of inner types for scala.Option[T] when T is a primitive type like Int,
  // the only way to ensure generated swagger doc has int type for number2 is to use an annotation
  case class AddOptionRequest(number: Int,
                              @Schema(required = false, implementation = classOf[Int]) number2: Option[Int] = None)
  case class AddOptionResponse(sum: Int)
}

class AddOptionActor extends Actor {
  import AddOptionActor._

  def receive: Receive = {
    case request: AddOptionRequest => {
      sender ! AddOptionResponse(request.number + request.number2.getOrElse(0))
    }
  }
}