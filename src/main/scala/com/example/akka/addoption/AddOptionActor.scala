package com.example.akka.addoption

import akka.actor.Actor
import io.swagger.v3.oas.annotations.media.Schema

object AddOptionActor {
  // due to type erasure of inner types for scala.Option[T] when T is a primitive type like Int or Boolean,
  // the only way to ensure generated swagger doc has int/boolean type for number2 is to use an annotation
  case class AddOptionRequest(number: Boolean,
                              @Schema(required = false, implementation = classOf[Boolean]) number2: Option[Boolean] = None)
  case class AddOptionResponse(sum: Int)
}

class AddOptionActor extends Actor {
  import AddOptionActor._

  def receive: Receive = {
    case request: AddOptionRequest => {
      sender ! AddOptionResponse(booleanToInt(request.number) + request.number2.map(booleanToInt).getOrElse(0))
    }
  }

  private def booleanToInt(b: Boolean) = if (b) 1 else 0
}