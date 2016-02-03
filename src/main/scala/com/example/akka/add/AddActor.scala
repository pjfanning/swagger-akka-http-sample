package com.example.akka.add

import akka.actor.{Actor, ActorLogging}

object AddActor {
  case class AddRequest(numbers: Array[Int])
  case class AddResponse(sum: Int)
}

class AddActor extends Actor with ActorLogging {
  import AddActor._

  def receive: Receive = {
    case request: AddRequest => { sender ! AddResponse(request.numbers.reduce(_ + _)) }
  }
}