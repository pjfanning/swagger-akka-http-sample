package com.example.akka

import akka.http.scaladsl.server.RouteConcatenation
import ch.megard.akka.http.cors.CorsDirectives._
import com.example.akka.add.AddService
import com.example.akka.hello.HelloService
import com.example.akka.swagger.SwaggerDocService

/**
 * The REST API layer. It exposes the REST services, but does not provide any
 * web server interface.<br/>
 * Notice that it requires to be mixed in with ``core.CoreActors``, which provides access
 * to the top-level actors that make up the system.
 */
trait Api extends RouteConcatenation {
  this: CoreActors with Core =>

  private implicit val _ = system.dispatcher

  val routes =
    cors() (new AddService(add).route ~
    new HelloService(hello).route ~
    new SwaggerDocService(system).routes)

}
