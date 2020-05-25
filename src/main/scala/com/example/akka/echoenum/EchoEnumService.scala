package com.example.akka.echoenum

import akka.http.scaladsl.server.Directives
import com.example.akka.DefaultJsonFormats
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.ws.rs.core.MediaType
import javax.ws.rs.{Consumes, POST, Path, Produces}
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

@Path("/echoenum")
object EchoEnumService extends Directives with DefaultJsonFormats {

  //case class EchoEnum(@Schema(required = true, `type` = "string", allowableValues = Array("TALL", "GRANDE", "VENTI"))
  //                    enumValue: SizeEnum.Value)
  class SizeEnumTypeClass extends TypeReference[SizeEnum.type]
  case class EchoEnum(@JsonScalaEnumeration(classOf[SizeEnumTypeClass]) enumValue: SizeEnum.Value)

  implicit val enumFormat =
    new RootJsonFormat[SizeEnum.Value] {
      def write(obj: SizeEnum.Value): JsValue = JsString(obj.toString)
      def read(json: JsValue): SizeEnum.Value = {
        json match {
          case JsString(txt) => SizeEnum.withName(txt)
          case somethingElse => throw DeserializationException(s"Expected a value from enum $SizeEnum instead of $somethingElse")
        }
      }
    }
  implicit val echoEnumFormat = jsonFormat1(EchoEnum)

  val route = echo

  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
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
