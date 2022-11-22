name := "swagger-akka-http-sample"

scalaVersion := "2.13.10"

val akkaVersion = "2.6.20"
val akkaHttpVersion = "10.2.10"
val jacksonVersion = "2.14.1"
val swaggerVersion = "2.2.7"

//resolvers ++= Resolver.sonatypeOssRepos("snapshots")

val swaggerDependencies = Seq(
  "jakarta.ws.rs" % "jakarta.ws.rs-api" % "3.0.0",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.9.1",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.8.2",
  "com.github.swagger-akka-http" %% "swagger-enumeratum-module" % "2.5.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % swaggerVersion
)

libraryDependencies ++= Seq(
  "pl.iterators" %% "kebs-spray-json" % "1.9.5",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "1.1.3",
  "org.slf4j" % "slf4j-simple" % "2.0.4"
) ++ swaggerDependencies
