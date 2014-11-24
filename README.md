scalaz-scalatest
================

[![Build Status](https://travis-ci.org/bwmcadams/scalaz-scalatest.svg?branch=master)](https://travis-ci.org/bwmcadams/scalaz-scalatest)

Scalatest bindings for scalaz.  Partially inspired by scalaz-specs2.

Apache 2.0 licensed.


**Current Version:** 0.2.0

*Some stuff is still in flight as we work with Scalatest to simplify some syntax. Changes will be minor but may effect matcher syntax for the next few releases.*

Matchers & Helpers are presently offered for testing of the following scalaz concepts:
* Disjunction – aka `\/` ( with extra helpers similar to Scalatest `OptionValues` )
* `Validation`

## Setup  

We currently crossbuild for Scala 2.10 & 2.11. Test builds run against OpenJDK 6 & 7, and OracleJDK 7 & 8.

We are working on integrating into the Typelevel package on Maven central, but for now you'll need to add the private bintray to access the dependency: 
```sbt
resolvers += "BWMcAdams Bintray" at "http://dl.bintray.com/bwmcadams/maven"
```

And then add the dependency (substituting version # as necessary):

```sbt
libraryDependencies += "org.typelevel" %% "scalaz-scalatest" % "0.2.0" % test
```
## Usage

There are two ways to use the provided matchers. 

* Mix-In

```scala
class MySillyWalkSpec extends FlatSpec with Matchers with DisjunctionMatchers { 
  // ...
} 
```
This makes the matchers in `DisjunctionMatchers` available inside the scope of your test. 

You can also import explicitly from a provided object, instead.

```scala
import org.typelevel.scalatest.DisjunctionMatchers

class MySillyWalkSpec extends FlatSpec with Matchers { 
  import DisjunctionMatchers._
  // ...
}

```

Also brings the matchers into scope.

And now, the matchers themselves.

### DisjunctionMatchers

Disjunction matchers are provided for testing of `\/` instances. In addition, see below for information on the special `DisjunctionValues` helpers.



## Contributors

* [Brendan McAdams](http://github.com/bwmcadams) [bwmcadams]
* [Jason Swartz](http://github.com/swartzrock) [swartzrock]
* [Ryan Delucchi](http://github.com/ryanonsrc) [ryanonsrc]
* [Colt Frederickson](http://github.com/coltfred) [coltfred]
* [Thomas Lockney](http://github.com/tlockney) [tlockney]
