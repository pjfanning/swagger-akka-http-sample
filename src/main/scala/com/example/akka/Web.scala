package com.example.akka

import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

trait Web {
  this: Api with CoreActors with Core =>
  
  implicit val materializer = ActorMaterializer()
  
  Http().bindAndHandle(routes, "0.0.0.0", 12345)
}