package com.example.akka.hello

import javax.ws.rs.{GET, POST, Path}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.add.AddActor.{AddRequest, AddResponse}
import com.example.akka.hello.HelloActor._
import io.swagger.oas.annotations.{Operation, Parameter}
import io.swagger.oas.annotations.media.{Content, Schema}
import io.swagger.oas.annotations.parameters.RequestBody
import io.swagger.oas.annotations.responses.ApiResponse

@Path("/hello")
class HelloService(hello: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout = Timeout(2.seconds)
  implicit val greetingFormat = jsonFormat1(Greeting)

  val route =
    getHello ~
    getHelloSegment

  @GET
  @Operation(summary = "Return Hello greeting (anonymous)", description = "Return Hello greeting for anonymous request",
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Add response",
        content = new Content(schema = new Schema(implementation = classOf[Greeting]))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def getHello =
    path("hello") {
      get {
        complete { (hello ? AnonymousHello).mapTo[Greeting] }
      }
    }

  @POST
  @Operation(summary = "Return Hello greeting", description = "Return Hello greeting for named user",
    parameters = Array(new Parameter(name = "name", in = "path", description = "user name")),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Add response"),
      new ApiResponse(responseCode = "500", description = "Internal server error",
        content = new Content(schema = new Schema(implementation = classOf[Greeting]))))
  )
  def getHelloSegment =
    path("hello" / Segment) { name =>
      get {
        complete { (hello ? Hello(name)).mapTo[Greeting] }
      }
    }
}

