name := "scalaz-scalatest"
organization := "org.typelevel"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.10.6", "2.11.8")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.9",
  "org.scalatest" %% "scalatest" % "2.2.6"
)

resolvers += Resolver.sonatypeRepo("releases")
licenses := Seq("Apache-2.0" → url("http://www.opensource.org/licenses/Apache-2.0"))
homepage := Some(url("http://github.com/typelevel/scalaz-scalatest"))
bintrayOrganization := Some("typelevel")
bintrayRepository := "releases"

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
    <developer>
      <id>coltfred</id>
      <name>Colt Frederickson</name>
    </developer>
  </developers>
}
