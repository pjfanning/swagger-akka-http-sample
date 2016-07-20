name := "swagger-akka-http-sample"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Maven" at "https://repo1.maven.org/maven2/"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.7.2",
  "org.slf4j" % "slf4j-simple" % "1.7.14"
)
