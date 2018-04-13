package com.example.akka.echoenum

import javax.ws.rs.Path

import scala.annotation.meta.field

import akka.http.scaladsl.server.Directives
import com.example.akka.DefaultJsonFormats
import io.swagger.annotations._
import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

@Api(value = "/echoenum")
@Path("/echoenum")
object EchoEnumService extends Directives with DefaultJsonFormats {

  case class EchoEnum(
    @(ApiModelProperty @field)(value = "Enum Value", dataType = "String",
      allowableValues = "TALL,GRANDE,VENTI", required = true) enumValue: Enum.Value)

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

  @ApiOperation(value = "Echo", nickname = "echoenum", httpMethod = "POST", response = classOf[EchoEnum])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "enum value to echo", required = true,
        dataTypeClass = classOf[EchoEnum], paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Bad Request")
  ))
  def echo =
    path("echoenum") {
      post {
        entity(as[EchoEnum]) { request =>
          complete(request)
        }
      }
    }

}
