scalaz-scalatest
================

[![Build Status](https://travis-ci.org/typelevel/scalaz-scalatest.svg?branch=master)](https://travis-ci.org/typelevel/scalaz-scalatest)
[![codecov.io](https://codecov.io/github/typelevel/scalaz-scalatest/coverage.svg?branch=master)](https://codecov.io/github/typelevel/scalaz-scalatest?branch=master)
[![scaladoc](https://javadoc-badge.appspot.com/org.typelevel/scalaz-scalatest_2.11.svg?label=scaladoc)](https://javadoc-badge.appspot.com/org.typelevel/scalaz-scalatest_2.11)

Scalatest bindings for scalaz.

## Setup

We currently crossbuild for Scala 2.10, 2.11 & 2.12.

|Scalaz-Scalatest Version | Scalaz Version  | Scalatest Version |
|-------------------------| --------------  | ----------------- |
| 0.4.0                   | 7.1.x           | 2.2.6             |
| 0.5.2                   | 7.1.x           | 3.0.0             |
| 1.0.0                   | 7.2.x           | 2.2.6             |
| 1.1.2                   | 7.2.x           | 3.0.0             |

**Disclaimer** Only the scalatest 3.0.0 versions are available for Scala 2.12. Feel free to open an issue if you need Scalatest 2.2.x with 2.12.x.

To include this in your project, add the dependency (substituting version # as necessary):

```sbt
libraryDependencies += "org.typelevel" %% "scalaz-scalatest" % "1.1.1" % "test"
```

## What does this provide?

Matchers & Helpers are presently offered for testing of the following scalaz concepts:
* Disjunction - aka `\/` 
* `Validation`
* `Task`

## Usage

There are two ways to use the provided matchers:

You can mix them in:

```scala
class MySillyWalkSpec extends FlatSpec with Matchers with DisjunctionMatchers { 
  // ...
} 
```
This makes the matchers in `DisjunctionMatchers` available inside the scope of your test. 


You can also import explicitly from a provided object:

```scala
import org.typelevel.scalatest.DisjunctionMatchers

class MySillyWalkSpec extends FlatSpec with Matchers { 
  import DisjunctionMatchers._
  // ...
}

```

Also brings the matchers into scope.

And now, the matchers themselves.

## Disjunction Matchers

DisjunctionMatchers supplies the following methods:

```
beLeft[E](element: E)
left[E]
beRight[T](element: T)
right[T]
```

### Specific Element Matchers

The matchers that begin with a be prefix are for matching a specific element inside of the `\/`.

Something like the following:

```
val s = "Hello World"
val valueInRight = \/.right(s)

//This passes
valueInRight should beRight(s)

//This fails with the following message:
//\/-(Hello World) did not contain an \/- element matching 'goodbye'.
valueInRight should beRight("goodbye")
```

The matchers work the same for `beLeft`.

### Right and Left Matchers

The `left` and `right` matchers are for checking to see if the `\/` is a right or left without caring what's inside.

```
  //This passes
  \/.left("uh oh") should be(left)
  
  //This fails with the following message:
  //-\/(uh oh) was not an \/-, but should have been.
  \/.left("uh oh") should be(right)
```

## Validation Matchers

scalaz.Validation also has matchers similar to the ones described above.

```
def beFailure[E](element: E)
def failure[E]
def success[T]
def beSuccess[T](element: T)
```

I won't repeat how they're used here. `Validation` does have an additional matcher though which allows
you to describe values that are in the `Failure` if you're using `ValidationNel`.

This matcher is `haveFailure` and can be used like this:

```
val validationNelValue: ValidationNel[String, Int] = Validation.failure(NonEmptyList("error1", "error2"))

//The following works fine:
validationNelValue should haveFailure("error1")

//But you can also combine them with the and word to match multiple values:
validateNelValue should (haveFailure("error1") and haveFailure("error2"))
```


## Values Helpers

A very common test idiom is to want to assert the `\/` is a left or a right and then extract the value. For this
we supply `DisjunctionValues`. This can be mixed into your test or imported as an object just like the matchers above, but 
instead of providing Matchers it instead adds `value` and `leftValue` as syntax to the `\/` type.

```
val x = \/.right("hello")
//Passes!
x.value shouldBe "hello" 

//Fails with the following message:
//    'Hello' is \/-, expected -\/.
x.leftValue shouldBe "hello" 
```

The same is true for the `Validation`. If you import or mixin `ValidationValues` you'll be able to call `.value` to extract
`Success` and `.leftValue` to extract the `Failure` side.

A similar thing is provided for `scalaz.concurrent.Task`. If you import or mixin `TaskValues` you can call `.runValue` (Scalaz 7.1) or `.syncValue` (Scalaz 7.2) to run the `Task` to completion and extract the value. You can also call `failValue` to run the `Task` to completion and extract the failure. 
## Documentation and Support
* See the [scaladoc](https://javadoc-badge.appspot.com/org.typelevel/scalaz-scalatest_2.11).
* The [tests](https://github.com/typelevel/scalaz-scalatest/tree/master/src/test/scala) show usage.
* Yell at [@coltfred](https://twitter.com/coltfred) on twitter or drop by #scalaz on freenode.


## Contributors

* [Brendan McAdams](http://github.com/bwmcadams) [bwmcadams]
* [Colt Frederickson](http://github.com/coltfred) [coltfred]
* [Gavin Bisesi](http://github.com/Daenyth) [Daenyth]
* [Thomas Lockney](http://github.com/tlockney) [tlockney]
* [Ryan Delucchi](http://github.com/ryanonsrc) [ryanonsrc]
* [Jason Swartz](http://github.com/swartzrock) [swartzrock]


Partially inspired by scalaz-specs2. Predecesor to [cats-scalatest](https://github.com/IronCoreLabs/cats-scalatest).
