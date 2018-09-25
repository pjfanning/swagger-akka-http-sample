name := "swagger-akka-http-sample"

scalaVersion := "2.12.6"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

val akkaVersion = "2.5.16"
val akkaHttpVersion = "10.1.5"

libraryDependencies ++= Seq(
  "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.0.0",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.0.1-SNAPSHOT",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "0.3.0",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
)
