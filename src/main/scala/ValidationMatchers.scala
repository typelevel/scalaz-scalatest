package net.evilmonkeylabs.scalatest

import org.scalatest.matchers.{BeMatcher, MatchResult, Matcher}

import scalaz.{Failure, NonEmptyList, Success, Validation, ValidationNel}


trait ValidationMatchers {
  import scala.language.implicitConversions

  /**
   * Checks if a scalaz.ValidationNel contains a specific failure element
   * Usage:
   *  validationObj should haveFailure (someErrorMessageOrObject)
   *
   * Can also be used to test multiple elements:
   *  validationObj should (haveFailure (someErrorMessageOrObject) and
   *                       haveFailure (someOtherErrorMessageOrObject))
   *
   */
  def haveFailure[E](element: E): Matcher[ValidationNel[E, _]] = HasScalazFailureNelMatcher[E](element)

  /**
   * Checks if a scalaz.Validation is a specific failure element
   * @param element
   * @tparam E
   * @return
   */
  def failure[E](element: E): Matcher[Validation[E, _]] = BeScalazFailureMatcher[E](element)

  def failure[E]: BeMatcher[Validation[E, _]] = new IsScalazFailureMatcher[E]

  /**
   * Checks blindly if a `scalaz.Validation` is an instance of success.
   *
   * Because in ScalaZ `ValidationNel[Fail, Succeed]` is just an alias for `Validation[NonEmptyList[Fail], Success]`
   * this should catch both types
   *
   */
  def success[T]: BeMatcher[Validation[_, T]] = new IsScalazSuccessMatcher[T]


  case class HasScalazFailureNelMatcher[E](element: E) extends Matcher[ValidationNel[E, _]] {
    def apply(validation: ValidationNel[E, _]): MatchResult = {
      MatchResult(
        validation match {
          case Success(_) =>
            false
          case Failure(n: NonEmptyList[E]) =>
            n.list contains element
          case _ =>
            false
        },
        s"$validation did not contain a Failure element matching '$element'.",
        s"$validation contained a Failure element matching '$element', but should not have."
      )
    }
  }

  case class BeScalazFailureMatcher[E](element: E) extends Matcher[Validation[E, _]] {
    def apply(validation: Validation[E, _]): MatchResult = {
      MatchResult(
        validation match {
          case Success(_) =>
            false
          case Failure(`element`) =>
            true
          case _ =>
            false
        },
        s"$validation did not contain a Failure element matching '$element'.",
        s"$validation contained a Failure element matching '$element', but should not have."
      )
    }
  }


  class IsScalazSuccessMatcher[T] extends BeMatcher[Validation[_, T]] {
    def apply(validation: Validation[_, T]) = MatchResult(
      validation.isSuccess,
      s"$validation was not Success(_), but should have been.",
      s"$validation was Success(_), but should *NOT* have been."
    )
  }


  // SHOULD still catch NEL Based on type aliasing...
  class IsScalazFailureMatcher[E] extends BeMatcher[Validation[E, _]] {
    def apply(validation: Validation[E, _]) = MatchResult(
      validation.isFailure,
      s"$validation was not a Failure, but should have been.",
      s"$validation was a Failure, but should *NOT* have been."
    )
  }


}

/**
 * Import singleton in case you prefer to import rather than mix in
 */
object ValidationMatchers extends ValidationMatchers