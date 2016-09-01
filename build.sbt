name := "scalaz-scalatest"
organization := "org.typelevel"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.10.6", "2.11.8")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.5",
  "org.scalatest" %% "scalatest" % "2.2.6"
)

resolvers += Resolver.sonatypeRepo("releases")
