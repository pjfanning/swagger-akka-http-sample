package com.example.akka.add

import akka.actor.Actor

object AddActor {
  case class AddRequest(numbers: Array[Int])
  case class AddResponse(sum: Int)
}

class AddActor extends Actor {
  import AddActor._

  def receive: Receive = {
    case request: AddRequest => { sender ! AddResponse(request.numbers.reduce(_ + _)) }
  }
}