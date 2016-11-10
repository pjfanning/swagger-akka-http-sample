name := "swagger-akka-http-sample"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("public")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += DefaultMavenRepository 

libraryDependencies ++= Seq(
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.7.3",
  "org.slf4j" % "slf4j-simple" % "1.7.14"
)
