name := "scalaz-scalatest"
organization := "org.typelevel"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.10.6", "2.11.8")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.10",
  "org.scalatest" %% "scalatest" % "3.0.0"
)

resolvers += Resolver.sonatypeRepo("releases")
