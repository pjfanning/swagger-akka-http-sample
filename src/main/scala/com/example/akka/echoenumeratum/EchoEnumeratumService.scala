package com.example.akka.echoenumeratum

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.ws.rs.core.MediaType
import javax.ws.rs.{Consumes, POST, Path, Produces}
import pl.iterators.kebs.json.{KebsEnumFormats, KebsSpray}
import spray.json.DefaultJsonProtocol

@Path("/echoenumeratum")
object EchoEnumeratumService extends Directives with SprayJsonSupport with DefaultJsonProtocol
  with KebsSpray with KebsEnumFormats {

  case class EchoEnumeratum(enumValue: SizeEnum)

  implicit val echoEnumeratumFormat = jsonFormatN[EchoEnumeratum]

  val route = echo

  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Operation(summary = "Echo Enumeratum", description = "Echo Enumeratum",
    requestBody = new RequestBody(content = Array(new Content(schema = new Schema(implementation = classOf[EchoEnumeratum])))),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Echo Enumeratum",
        content = Array(new Content(schema = new Schema(implementation = classOf[EchoEnumeratum])))),
      new ApiResponse(responseCode = "400", description = "Bad Request"))
  )
  def echo =
    path("echoenumeratum") {
      post {
        entity(as[EchoEnumeratum]) { request =>
          complete(request)
        }
      }
    }

}
