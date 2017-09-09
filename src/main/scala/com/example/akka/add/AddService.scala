package com.example.akka.add

import javax.ws.rs.{POST, Path}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.add.AddActor._
import io.swagger.oas.annotations.Operation
import io.swagger.oas.annotations.media.{Content, Schema}
import io.swagger.oas.annotations.parameters.RequestBody
import io.swagger.oas.annotations.responses.ApiResponse

@Path("/add")
class AddService(addActor: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout = Timeout(2.seconds)

  implicit val requestFormat = jsonFormat1(AddRequest)
  implicit val responseFormat = jsonFormat1(AddResponse)

  val route = add

  @POST
  @Operation(summary = "Add integers", description = "Add integers",
    requestBody = new RequestBody(content = Array(new Content(schema = new Schema(implementation = classOf[AddRequest])))),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Add response",
        content = new Content(schema = new Schema(implementation = classOf[AddResponse]))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def add =
    path("add") {
      post {
        entity(as[AddRequest]) { request =>
          complete { (addActor ? request).mapTo[AddResponse] }
        }
      }
    }

}
