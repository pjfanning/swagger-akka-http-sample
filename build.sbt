name := "swagger-akka-http-sample"

scalaVersion := "2.12.2"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

val akkaVersion = "2.5.3"
val akkaHttpVersion = "10.0.8"

libraryDependencies ++= Seq(
  "io.swagger" % "swagger-jaxrs" % "1.5.15",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.9.2",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "0.2.1",
  "org.slf4j" % "slf4j-simple" % "1.7.22"
)
