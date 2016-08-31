scalaz-scalatest
================

[![Build Status](https://travis-ci.org/typelevel/scalaz-scalatest.svg?branch=master)](https://travis-ci.org/typelevel/scalaz-scalatest)
[![codecov.io](https://codecov.io/github/typelevel/scalaz-scalatest/coverage.svg?branch=master)](https://codecov.io/github/typelevel/scalaz-scalatest?branch=master)
[![scaladoc](https://javadoc-badge.appspot.com/org.typelevel/scalaz-scalatest_2.11.svg?label=scaladoc)](https://javadoc-badge.appspot.com/org.typelevel/scalaz-scalatest_2.11)
Scalatest bindings for scalaz.  Partially inspired by scalaz-specs2.

Apache 2.0 licensed.


**Current Version:** 0.3.0

*Some stuff is still in flight as we work with Scalatest to simplify some syntax. Changes will be minor but may effect matcher syntax for the next few releases.*

Matchers & Helpers are presently offered for testing of the following scalaz concepts:
* Disjunction – aka `\/` ( with extra helpers similar to Scalatest `OptionValues` )
* `Validation` ( with extra helpers similar to Scalatest `OptionValues` )

## Setup  

We currently crossbuild for Scala 2.10 & 2.11. Test builds run against OpenJDK 6 & 7, and OracleJDK 7 & 8.

(NOTE: *As of November 24, 2014, The project should be synced to Maven central*)

To include this in your project, add the dependency (substituting version # as necessary):

```sbt
libraryDependencies += "org.typelevel" %% "scalaz-scalatest" % "0.3.0" % "test"
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

### `DisjunctionMatchers`

Disjunction matchers are provided for testing of `\/` instances. In addition, see below for information on the special `DisjunctionValues` helpers.

#### `DisjunctionValues`

If you would like to assume the right-bias of `\/` in your tests, we provide a "Values" helper similar to Scalatest's own `OptionValues`. This lets you invoke `value` and then treat it as if it is `\/-` (right). If it *isn't* right, the test will fail.

This can be mixed in or explicitly imported via Object helper.
```scala
import org.typelevel.scalatest.DisjunctionValues
import DisjunctionValues._

val thisRecord = "I will not buy this record, it is scratched."
val thisTobacconist = "Ah! I will not buy this tobacconist's, it is scratched."
val hovercraft = "Yes, cigarettes. My hovercraft is full of eels."

it("should return the value inside a disjunction (\\/) if that disjunction is \\/- (right)") {
  val r: String \/ String = \/.right(thisRecord)
  r.value should === (thisRecord)
}

it("should throw TestFailedException if that disjunction (\\/) is -\\/ (left) ") {
  val r: String \/ String = \/.left(thisTobacconist)
  val caught =
    evaluating {
      r.value should === (thisRecord)
    } should produce [TestFailedException]
```

## Contributors

* [Brendan McAdams](http://github.com/bwmcadams) [bwmcadams]
* [Colt Frederickson](http://github.com/coltfred) [coltfred]
* [Gavin Bisesi](http://github.com/Daenyth) [Daenyth]
* [Thomas Lockney](http://github.com/tlockney) [tlockney]
* [Ryan Delucchi](http://github.com/ryanonsrc) [ryanonsrc]
* [Jason Swartz](http://github.com/swartzrock) [swartzrock]
