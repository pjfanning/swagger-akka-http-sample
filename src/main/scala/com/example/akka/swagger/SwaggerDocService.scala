package com.example.akka.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import com.example.akka.add.AddService
import com.example.akka.addoption.AddOptionService
import com.example.akka.echoenum.EchoEnumService
import com.example.akka.echoenumeratum.EchoEnumeratumService
import com.example.akka.echolist.EchoListService
import com.example.akka.hello.HelloService
import io.swagger.v3.oas.models.ExternalDocumentation

object SwaggerDocService extends SwaggerHttpService {
  override val apiClasses = Set(classOf[AddService], classOf[AddOptionService], classOf[HelloService],
    EchoEnumService.getClass, EchoEnumeratumService.getClass, EchoListService.getClass)
  override val host = "localhost:12345"
  override val info: Info = Info(version = "1.0")
  override val externalDocs: Option[ExternalDocumentation] = Some(new ExternalDocumentation().description("Core Docs").url("http://acme.com/docs"))
  //override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
  override val unwantedDefinitions = Seq("Function1", "Function1RequestContextFutureRouteResult")
}