package com.example.akka.echolist

import akka.http.scaladsl.server.Directives
import com.example.akka.DefaultJsonFormats
import io.swagger.annotations.{Api, ApiImplicitParam, ApiImplicitParams, ApiOperation, ApiResponse, ApiResponses}
import javax.ws.rs.Path
import pl.iterators.kebs.json.KebsSpray

@Api(value = "/echolist", produces = "application/json")
@Path("/echolist")
object EchoListService extends Directives with DefaultJsonFormats with KebsSpray {

  case class EchoList(listName: String, values: Seq[String])

  implicit val echoListFormat = jsonFormatN[EchoList]

  val route = echo

  @ApiOperation(value = "Echo List", nickname = "echoList", httpMethod = "POST", response = classOf[EchoList])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "data to echo", required = true,
      dataTypeClass = classOf[EchoList], paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 500, message = "Internal server error")
  ))
  def echo =
    path("echolist") {
      post {
        entity(as[EchoList]) { request =>
          complete(request)
        }
      }
    }

}
