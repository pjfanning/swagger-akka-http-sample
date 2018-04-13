package com.example.akka.echoenum

import javax.ws.rs.{GET, Path}

import akka.http.scaladsl.server.Directives
import com.example.akka.DefaultJsonFormats
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

@Path("/echoenum")
object EchoEnumService extends Directives with DefaultJsonFormats {

  case class EchoEnum(
    @Schema(required = true, `type` = "string", allowableValues = Array("TALL", "GRANDE", "VENTI")) enumValue: Enum.Value)

  implicit val enumFormat =
    new RootJsonFormat[Enum.Value] {
      def write(obj: Enum.Value): JsValue = JsString(obj.toString)
      def read(json: JsValue): Enum.Value = {
        json match {
          case JsString(txt) => Enum.withName(txt)
          case somethingElse => throw DeserializationException(s"Expected a value from enum $Enum instead of $somethingElse")
        }
      }
    }
  implicit val echoEnumFormat = jsonFormat1(EchoEnum)

  val route = echo

  @GET
  @Operation(summary = "Echo Enum", description = "Echo Enum",
    requestBody = new RequestBody(content = Array(new Content(schema = new Schema(implementation = classOf[EchoEnum])))),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Echo Enum",
        content = Array(new Content(schema = new Schema(implementation = classOf[EchoEnum])))),
      new ApiResponse(responseCode = "400", description = "Bad Request"))
  )
  def echo =
    path("echoenum") {
      post {
        entity(as[EchoEnum]) { request =>
          complete(request)
        }
      }
    }

}
