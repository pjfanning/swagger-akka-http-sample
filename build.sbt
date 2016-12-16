name := "swagger-akka-http-sample"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("public")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += DefaultMavenRepository

val akkaHttpVersion = "2.4.11"

libraryDependencies ++= Seq(
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.8.1" excludeAll(
    ExclusionRule(organization = "com.typesafe.akka")
  ),
  "ch.megard" %% "akka-http-cors" % "0.1.10" excludeAll(
    ExclusionRule(organization = "com.typesafe.akka")
  ),
  "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaHttpVersion,
  "org.slf4j" % "slf4j-simple" % "1.7.14"
)
