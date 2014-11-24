name := "scalaz-scalatest"
version := "0.3.0"
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
homepage := Some(url("http://github.com/typelevel/scalaz-scalatest"))

bintraySettings
bintrayResolverSettings
bintraySettings

pomIncludeRepository := { _ => false }
pomExtra := {
  <scm>
    <url>git@github.com:typelevel/scalaz-scalatest.git</url>
    <connection>scm:git:git@github.com:typelevel/scalaz-scalatest.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bwmcadams</id>
      <name>Brendan McAdams</name>
    </developer>
  </developers>
}
