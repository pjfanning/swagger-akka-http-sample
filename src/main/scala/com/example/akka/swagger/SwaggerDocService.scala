package com.example.akka.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import com.example.akka.add.AddService
import com.example.akka.addoption.AddOptionService
import com.example.akka.echoenum.EchoEnumService
import com.example.akka.hello.HelloService
import io.swagger.models.ExternalDocs
import io.swagger.models.auth.BasicAuthDefinition

object SwaggerDocService extends SwaggerHttpService {
  override val apiClasses = Set(classOf[AddService], classOf[AddOptionService], classOf[HelloService], EchoEnumService.getClass)
  override val host = "localhost:12345"
  override val info = Info(version = "1.0")
  override val externalDocs = Some(new ExternalDocs("Core Docs", "http://acme.com/docs"))
  override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
  override val unwantedDefinitions = Seq("Function1", "Function1RequestContextFutureRouteResult")
}