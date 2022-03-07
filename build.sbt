name := "swagger-akka-http-sample"

scalaVersion := "2.13.8"

val akkaVersion = "2.6.18"
val akkaHttpVersion = "10.2.9"
val jacksonVersion = "2.13.2"
val swaggerVersion = "2.1.13"

//resolvers += Resolver.sonatypeRepo("snapshots")

val swaggerDependencies = Seq(
  "jakarta.ws.rs" % "jakarta.ws.rs-api" % "3.0.0",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.6.0",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.5.2",
  "com.github.swagger-akka-http" %% "swagger-enumeratum-module" % "2.3.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % swaggerVersion
)

/**
 * Leave out swaggerUIDependencies if you don't want to include the swaggerUI.
 * See also SwaggerDocService
 */
val swaggerUIDependencies = Seq(
  "org.webjars" % "webjars-locator" % "0.45",
  "org.webjars" % "swagger-ui" % "4.5.2",
)

libraryDependencies ++= Seq(
  "pl.iterators" %% "kebs-spray-json" % "1.9.4",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "1.1.3",
  "org.slf4j" % "slf4j-simple" % "1.7.36"
) ++ swaggerDependencies ++ swaggerUIDependencies
