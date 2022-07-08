package com.example.akka.addduration

import akka.actor.Actor
import com.example.akka.addduration.AddDurationActor._

import scala.concurrent.duration.{Duration, DurationLong}

object AddDurationActor {
  case class AddDurationRequest(durations: Seq[Duration])
  case class AddDurationResponse(duration: Duration)
}

class AddDurationActor extends Actor {

  def receive: Receive = {
    case request: AddDurationRequest => sender ! AddDurationResponse(addDurations(request.durations))
  }

  private def addDurations(durations: Seq[Duration]): Duration = durations match {
    case Seq() => 0.seconds
    case head :: tail => head + addDurations(tail)
  }
}
