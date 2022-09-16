package com.example.akka.animal

import akka.actor.ActorRef
import akka.http.scaladsl.server.{Directives, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.animal.AnimalActor._
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.{GET, Path, Produces}
import spray.json.RootJsonFormat

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

@Path("/animal")
class AnimalService(actorRef: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout: Timeout = Timeout(2.seconds)
  implicit val catFormat: RootJsonFormat[Cat] = jsonFormat2(Cat)

  val route: Route =
    getAnimal

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Return Animal", description = "Return Animal",
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Animal response",
        content = Array(new Content(schema = new Schema(implementation = classOf[Animal])))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def getAnimal: Route =
    path("animal") {
      get {
        complete { (actorRef ? AnimalRequest).mapTo[Cat] }
      }
    }
}

