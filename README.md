scalaz-scalatest
================

[![Build Status](https://travis-ci.org/bwmcadams/scalaz-scalatest.svg?branch=master)](https://travis-ci.org/bwmcadams/scalaz-scalatest)

Scalatest bindings for scalaz.

Partially inspired by scalaz-specs2.


**Current Version:** 0.2.0

## Setup  

We currently crossbuild for Scala 2.10 & 2.11. Test builds run against OpenJDK 6 & 7, and OracleJDK 7 & 8.

We are working on integrating into the Typelevel package on Maven central, but for now you'll need to add the private bintray to access the dependency: 
```sbt
resolvers += "BWMcAdams Bintray" at "http://dl.bintray.com/bwmcadams/maven"
```

And then add the dependency (substituting version # as necessary):

```sbt
libraryDependencies += "org.typelevel" %% "scalaz-scalatest" % "0.2.0"
```

## Contributors

* [Brendan McAdams](http://github.com/bwmcadams) [bwmcadams]
* [Jason Swartz](http://github.com/swartzrock) [swartzrock]
* [Ryan Delucchi](http://github.com/ryanonsrc) [ryanonsrc]
* [Colt Frederickson](http://github.com/coltfred) [coltfred]
* [Thomas Lockney](http://github.com/tlockney) [tlockney]
