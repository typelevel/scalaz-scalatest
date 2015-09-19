package org.typelevel.scalatest

import org.scalatest.{FunSpec, Matchers}
import Matchers._
import org.scalatest.OptionValues._
import org.scalatest.exceptions.TestFailedException
import scalaz.{ -\/, \/, \/- }
import scalaz.syntax.either._

class DisjunctionValuesSpec extends FunSpec with Util {
  import DisjunctionValues._

  describe("value on Disjunction") {
    it("should return the value inside a disjunction (\\/) if that disjunction is \\/- (right)") {
      val r: String \/ String = \/.right(thisRecord)
      r.value should === (thisRecord)
    }

    it("should throw TestFailedException if that disjunction (\\/) is -\\/ (left) ") {
      val r: String \/ String = \/.left(thisTobacconist)
      val caught =
        intercept[TestFailedException] {
          r.value should === (thisRecord)
        } 
      caught.failedCodeLineNumber.value should equal (thisLineNumber - 2)
      caught.failedCodeFileName.value should be ("DisjunctionValuesSpec.scala")
    }
  }

  describe("leftValue on Disjunction"){
    it("should return the value if it's left"){
      val r = \/.left(thisRecord)
      r.leftValue should === (thisRecord)
    }

    it("should throw TestFailedException if the disjunction is right"){
      val r = \/.right(thisRecord)
      val caught = intercept[TestFailedException] {
        r.leftValue
      } 
      caught.failedCodeLineNumber.value should equal (thisLineNumber - 2)
      caught.failedCodeFileName.value should be ("DisjunctionValuesSpec.scala")
    }
  }
}


