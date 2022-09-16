package com.example.akka

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteConcatenation
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import com.example.akka.add.{AddActor, AddService}
import com.example.akka.addoption.{AddOptionActor, AddOptionService}
import com.example.akka.animal.{AnimalActor, AnimalService}
import com.example.akka.echoenum.EchoEnumService
import com.example.akka.echoenumeratum.EchoEnumeratumService
import com.example.akka.echolist.EchoListService
import com.example.akka.hello.{HelloActor, HelloService}
import com.example.akka.swagger.SwaggerDocService

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContextExecutor, Future}

object Rest extends App with RouteConcatenation {
  implicit val system: ActorSystem = ActorSystem("akka-http-sample")
  sys.addShutdownHook(system.terminate())

  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val add = system.actorOf(Props[AddActor])
  val addOption = system.actorOf(Props[AddOptionActor])
  val hello = system.actorOf(Props[HelloActor])
  val animal = system.actorOf(Props[AnimalActor])

  val routes =
    cors() (new AddService(add).route ~
      new AddOptionService(addOption).route ~
      new HelloService(hello).route ~
      new AnimalService(animal).route ~
      EchoEnumService.route ~
      EchoEnumeratumService.route ~
      EchoListService.route ~
      SwaggerDocService.routes)

  val f = for {
    bindingFuture <- Http().newServerAt("0.0.0.0", 12345).bind(routes)
    waitOnFuture  <- Future.never
  } yield waitOnFuture

  Await.ready(f, Duration.Inf)
}
