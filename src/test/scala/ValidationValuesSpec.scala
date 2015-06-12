package org.typelevel.scalatest

import org.scalatest.{FunSpec, Matchers}
import Matchers._
import org.scalatest.OptionValues._
import org.scalatest.exceptions.TestFailedException
import scalaz.{Validation, Success, Failure}
import scalaz.syntax.validation._

class ValidationValuesSpec extends FunSpec with Util {
  import ValidationValues._

  describe("value on Validation") {
    it("should return the value inside a validation if that validation is Success") {
      val r: Validation[String, String] = Success(thisRecord)
      r.value should === (thisRecord)
    }

    it("should throw TestFailedException if that validation is Failure") {
      val r: Validation[String, String] = Failure(thisTobacconist)
      val caught =
        evaluating {
          r.value should === (thisRecord)
        } should produce [TestFailedException]
      caught.failedCodeLineNumber.value should equal (thisLineNumber - 2)
      caught.failedCodeFileName.value should be ("ValidationValuesSpec.scala")
    }
  }

  describe("leftValue on Validation"){
    it("should return the value if it's left"){
      val r = Failure(thisRecord)
      r.leftValue should === (thisRecord)
    }

    it("should throw TestFailedException if the validation is right"){
      val r = Success(thisRecord)
      val caught = evaluating{
        r.leftValue
      } should produce[TestFailedException]
      caught.failedCodeLineNumber.value should equal (thisLineNumber - 2)
      caught.failedCodeFileName.value should be ("ValidationValuesSpec.scala")
    }
  }
}


