package org.typelevel.scalatest

import org.scalatest.{ FunSpec, Matchers }
import Matchers._
import org.scalatest.OptionValues._
import org.scalatest.exceptions.TestFailedException
import scalaz.{ -\/, \/, \/- }
import scalaz.syntax.either._

class DisjunctionValuesSpec extends TestBase {
  import DisjunctionValues._

  "value on Disjunction" should {
    "return the value inside a disjunction (\\/) if that disjunction is \\/- (right)" in {
      val r: String \/ String = \/.right(thisRecord)
      r.value should ===(thisRecord)
    }

    "throw TestFailedException if that disjunction (\\/) is -\\/ (left) " in {
      val r: String \/ String = \/.left(thisTobacconist)
      val caught =
        intercept[TestFailedException] {
          r.value should ===(thisRecord)
        }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("DisjunctionValuesSpec.scala")
    }
  }

  "leftValue on Disjunction" should {
    "return the value if it's left" in {
      val r = \/.left(thisRecord)
      r.leftValue should ===(thisRecord)
    }

    "throw TestFailedException if the disjunction is right" in {
      val r = \/.right(thisRecord)
      val caught = intercept[TestFailedException] {
        r.leftValue
      }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("DisjunctionValuesSpec.scala")
    }
  }
}

