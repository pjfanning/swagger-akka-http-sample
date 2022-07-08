package com.example.akka.addduration

import akka.actor.ActorRef
import akka.http.scaladsl.server.{Directives, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.example.akka.DefaultJsonFormats
import com.example.akka.addduration.AddDurationActor.{AddDurationRequest, AddDurationResponse}
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.{Consumes, POST, Path, Produces}
import spray.json.{JsString, JsValue, JsonFormat, RootJsonFormat, deserializationError}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{Duration, DurationInt}

@Path("/addDuration")
class AddDurationService(addActor: ActorRef)(implicit executionContext: ExecutionContext)
  extends Directives with DefaultJsonFormats {

  implicit val timeout: Timeout = Timeout(2.seconds)

  implicit object DurationFormat extends JsonFormat[Duration] {
    def write(d: Duration) = JsString(d.toString)
    def read(json: JsValue) = json match {
      case JsString(str) => Duration(str)
      case _ => deserializationError("String expected")
    }
  }

  implicit val requestFormat: RootJsonFormat[AddDurationRequest] = jsonFormat1(AddDurationRequest)
  implicit val responseFormat: RootJsonFormat[AddDurationResponse] = jsonFormat1(AddDurationResponse)

  val route: Route = addDurations

  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Add durations", description = "Add durations",
    requestBody = new RequestBody(required = true,
      content = Array(new Content(schema = new Schema(implementation = classOf[AddDurationRequest])))),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Add response",
        content = Array(new Content(schema = new Schema(implementation = classOf[AddDurationResponse])))),
      new ApiResponse(responseCode = "500", description = "Internal server error"))
  )
  def addDurations: Route =
    path("addDuration") {
      post {
        entity(as[AddDurationRequest]) { request =>
          complete { (addActor ? request).mapTo[AddDurationResponse] }
        }
      }
    }

}
