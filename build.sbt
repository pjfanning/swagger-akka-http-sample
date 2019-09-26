name := "swagger-akka-http-sample"

scalaVersion := "2.13.1"

resolvers += Resolver.sonatypeRepo("releases")

val akkaVersion = "2.5.25"
val akkaHttpVersion = "10.1.10"
val jacksonVersion = "2.10.0"
val swaggerVersion = "2.0.9"

libraryDependencies ++= Seq(
  "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.0.3",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.0.5",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-core" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-annotations" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-models" % swaggerVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2" % swaggerVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "0.4.1",
  "org.slf4j" % "slf4j-simple" % "1.7.28"
)
