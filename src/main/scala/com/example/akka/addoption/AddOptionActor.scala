package com.example.akka.addoption

import akka.actor.Actor
import io.swagger.annotations.ApiModelProperty

import scala.annotation.meta.field

object AddOptionActor {
  // due to type erasure of inner types for scala.Option[T] when T is a primitive type like Int,
  // the only way to ensure generated swagger doc has int type for number2 is to use an annotation
  case class AddOptionRequest(number: Int,
                              @(ApiModelProperty @field)(dataType = "int") number2: Option[Int] = None)
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