name := "swagger-akka-http-sample"

scalaVersion := "2.13.1"

resolvers += Resolver.sonatypeRepo("releases")

val akkaVersion = "2.6.4"
val akkaHttpVersion = "10.1.11"

libraryDependencies ++= Seq(
  "io.swagger" % "swagger-jaxrs" % "1.6.0",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "1.1.1",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.megard" %% "akka-http-cors" % "0.4.1",
  "javax.xml.bind" % "jaxb-api" % "2.3.0", //https://github.com/swagger-akka-http/swagger-akka-http/issues/62
  "org.slf4j" % "slf4j-simple" % "1.7.30"
)
