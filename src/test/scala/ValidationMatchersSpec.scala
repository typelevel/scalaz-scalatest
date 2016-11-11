package org.typelevel.scalatest

import org.scalatest.{ Matchers, FlatSpec }

import scalaz.{ Failure, NonEmptyList, Success, Validation, ValidationNel }
import scalaz.syntax.validation._
import scalaz.syntax.apply._

class ValidationMatchersSpec extends TestBase with ValidationMatchers {

  val simpleFailureNel = thisRecord.failureNel *> thisTobacconist.failureNel
  "ValidationMatchers" should {
    "Match one specific element in a Validation failure NEL" in {
      simpleFailureNel should haveFailure(thisRecord)
    }

    "Match multiple specific elements in a Validation failure NEL" in {
      simpleFailureNel should (haveFailure(thisRecord) and
        haveFailure(thisTobacconist))
    }

    "Match a specific element of a single Validation failure" in {
      thisRecord.failure should beFailure(thisRecord)
    }

    "Test whether a Validation instance is a failure w/o specific element value" in {
      thisTobacconist.failure should be(failure)
    }

    "By negating 'failure', test whether a Validation instance is a success w/o specific element value" in {
      hovercraft.success should not be (failure)
    }

    "Test whether a Validation instance is a success w/o specific element value" in {
      hovercraft.success should be(success)
    }

    "By negating 'success', test whether a Validation instance is a failure w/o specific element value" in {
      thisTobacconist.failure should not be (success)
    }

    "Match a specific element of a single Validation success" in {
      hovercraft.success should beSuccess(hovercraft)
    }
  }
}
