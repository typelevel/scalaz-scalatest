name := "scalaz-scalatest"

version := "0.1.0"

organization := "org.typelevel"

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.0",
  "org.scalatest" %% "scalatest" % "2.2.2"
)

resolvers += Resolver.sonatypeRepo("releases")

licenses := Seq("Apache-2.0" → url("http://www.opensource.org/licenses/Apache-2.0"))

seq(bintraySettings:_*)

seq(bintrayResolverSettings:_*)

seq(bintraySettings:_*)
