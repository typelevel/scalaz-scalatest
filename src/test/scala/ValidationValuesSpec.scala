package org.typelevel.scalatest

import org.scalatest.{ FunSpec, Matchers }
import Matchers._
import org.scalatest.OptionValues._
import org.scalatest.exceptions.TestFailedException
import scalaz.{ Validation, Success, Failure }
import scalaz.syntax.validation._

class ValidationValuesSpec extends TestBase {
  import ValidationValues._

  "value on Validation" should {
    "return the value inside a validation if that validation is Success" in {
      val r: Validation[String, String] = Success(thisRecord)
      r.value should ===(thisRecord)
    }

    "throw TestFailedException if that validation is Failure" in {
      val r: Validation[String, String] = Failure(thisTobacconist)
      val caught =
        intercept[TestFailedException] {
          r.value should ===(thisRecord)
        }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("ValidationValuesSpec.scala")
    }
  }

  "leftValue on Validation" should {
    "return the value if it's left" in {
      val r = Failure(thisRecord)
      r.leftValue should ===(thisRecord)
    }

    "throw TestFailedException if the validation is right" in {
      val r = Success(thisRecord)
      val caught = intercept[TestFailedException] {
        r.leftValue
      }
      caught.failedCodeLineNumber.value should equal(thisLineNumber - 2)
      caught.failedCodeFileName.value should be("ValidationValuesSpec.scala")
    }
  }
}

