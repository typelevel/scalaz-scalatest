name := "scalaz-scalatest"

version := "0.1.0-SNAPSHOT"

organization := "net.evilmonkeylabs"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "org.scalatest" % "scalatest_2.11" % "2.2.1"
)

resolvers += Resolver.sonatypeRepo("releases")

licenses := Seq("MIT" → url("http://www.opensource.org/licenses/mit-license.php"))
