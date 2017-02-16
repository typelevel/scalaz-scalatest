name := "scalaz-scalatest"
organization := "org.typelevel"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

com.typesafe.sbt.SbtScalariform.scalariformSettings

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.11",
  "org.scalaz" %% "scalaz-concurrent" % "7.1.11",
  "org.scalatest" %% "scalatest" % "3.0.0"
)

resolvers += Resolver.sonatypeRepo("releases")
