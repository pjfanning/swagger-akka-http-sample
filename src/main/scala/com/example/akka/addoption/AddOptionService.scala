package com.example.akka.addoption

import javax.ws.rs.{Consumes, POST, Path, Produces}
import akka.actor.ActorRef
import akka.http.scaladsl.server.{Directives, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.addoption.AddOptionActor._
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.ws.rs.core.MediaType
import spray.json.RootJsonFormat

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

@Path("/addOption")
class AddOptionService(addActor: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout: Timeout = Timeout(2.seconds)

  implicit val requestFormat: RootJsonFormat[AddOptionRequest] = jsonFormat2(AddOptionRequest)
  implicit val responseFormat: RootJsonFormat[AddOptionResponse] = jsonFormat1(AddOptionResponse)

  val route: Route = addOption

  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Add integers", description = "Add integers",
    requestBody = new RequestBody(required = true,
      content = Array(new Content(schema = new Schema(implementation = classOf[AddOptionRequest])))),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Add response",
        content = Array(new Content(schema = new Schema(implementation = classOf[AddOptionResponse])))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def addOption: Route =
    path("addOption") {
      post {
        entity(as[AddOptionRequest]) { request =>
          complete { (addActor ? request).mapTo[AddOptionResponse] }
        }
      }
    }

}
