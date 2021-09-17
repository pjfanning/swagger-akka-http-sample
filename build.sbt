name := "swagger-akka-http-sample"

scalaVersion := "2.13.6"

val akkaVersion = "2.6.16"
val akkaHttpVersion = "10.2.6"
val jacksonVersion = "2.12.5"

//resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "io.swagger" % "swagger-jaxrs" % "1.6.2",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "1.5.1",
  "com.github.swagger-akka-http" %% "swagger-scala-module" % "1.3.0",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "ch.megard" %% "akka-http-cors" % "1.1.2",
  "pl.iterators" %% "kebs-spray-json" % "1.9.3",
  "javax.xml.bind" % "jaxb-api" % "2.3.1", //https://github.com/swagger-akka-http/swagger-akka-http/issues/62
  "org.slf4j" % "slf4j-simple" % "1.7.32"
)
