package com.example.akka.hello

import javax.ws.rs.{GET, Path, Produces}
import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.hello.HelloActor._
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import javax.ws.rs.core.MediaType

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

@Path("/hello")
class HelloService(hello: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout = Timeout(2.seconds)
  implicit val greetingFormat = jsonFormat1(Greeting)

  val route =
    getHello ~
    getHelloSegment

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Return Hello greeting (anonymous)", description = "Return Hello greeting for anonymous request",
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Hello response",
        content = Array(new Content(schema = new Schema(implementation = classOf[Greeting])))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def getHello =
    path("hello") {
      get {
        complete { (hello ? AnonymousHello).mapTo[Greeting] }
      }
    }

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Return Hello greeting", description = "Return Hello greeting for named user",
    parameters = Array(new Parameter(name = "name", in = ParameterIn.PATH, description = "user name")),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Hello response",
        content = Array(new Content(schema = new Schema(implementation = classOf[Greeting])))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def getHelloSegment =
    path("hello" / Segment) { name =>
      get {
        complete { (hello ? Hello(name)).mapTo[Greeting] }
      }
    }
}

