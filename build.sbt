name := "swagger-akka-http-sample"

scalaVersion := "2.12.8" //"2.13.0-M5"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

val akkaVersion = "2.5.22"
val akkaHttpVersion = "10.1.8"
val jacksonVersion = "2.9.9"
val swaggerVersion = "2.0.8"

libraryDependencies ++= Seq(
  "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.0.2",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.0.3",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
  "io.swagger.core.v3" % "swagger-core" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-annotations" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-models" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2" % swaggerVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "0.4.0",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
)
