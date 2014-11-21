package org.typelevel.scalatest

import org.scalatest.{Matchers, FlatSpec}

import scalaz.{Failure, NonEmptyList, Success, Validation, ValidationNel}
import scalaz.syntax.validation._
import scalaz.syntax.apply._

class ValidationMatchersSpec extends FlatSpec with Matchers with Util with ValidationMatchers {

  val simpleFailureNel = thisRecord.failureNel *>  thisTobacconist.failureNel

  it should "Match one specific element in a Validation failure NEL" in {
    simpleFailureNel should haveFailure (thisRecord)
  }

  it should "Match multiple specific elements in a Validation failure NEL" in {
    simpleFailureNel should (haveFailure (thisRecord) and
                             haveFailure (thisTobacconist))
  }

  it should "Match a specific element of a single Validation failure" in {
    thisRecord.failure should beFailure(thisRecord)
  }

  it should "Test whether a Validation instance is a failure w/o specific element value" in {
    thisTobacconist.failure should be (failure)
  }

  it should "By negating 'failure', test whether a Validation instance is a success w/o specific element value" in {
    hovercraft.success should not be (failure)
  }

  it should "Test whether a Validation instance is a success w/o specific element value" in {
    hovercraft.success should be (success)
  }

  it should "By negating 'success', test whether a Validation instance is a failure w/o specific element value" in {
    thisTobacconist.failure should not be (success)
  }

  it should "Match a specific element of a single Validation success" in {
    hovercraft.success should beSuccess(hovercraft)
  }
}
