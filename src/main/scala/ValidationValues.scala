package org.typelevel.scalatest

import org.scalatest.exceptions.{ TestFailedException, StackDepthException }
import org.scalactic.source

import scalaz.{ Validation, Success, Failure }
import scalaz.syntax.validation._

trait ValidationValues {
  import scala.language.implicitConversions

  /**
   * Implicit conversion that adds a `value` method to `scalaz.Validation`
   *
   * @param validation the `scalaz.Validation` on which to add the `value` method
   */
  implicit def convertValidationToValidationable[E, T](validation: Validation[E, T])(implicit pos: source.Position): Validationable[E, T] = new Validationable(validation, pos)

  // TODO: Fix me as a proper repl session example
  /**
   * Container class for matching success
   * type stuff in `scalaz.Validation` containers,
   * similar to `org.scalatest.OptionValues.Valuable`
   *
   * Meant to allow you to make statements like:
   *
   * <pre class="stREPL">
   *   result.value should be &gt; 15
   * </pre>
   *
   * Where it only matches if result is `Success(9)`
   *
   * Otherwise your test will fail, indicating that it was left instead of right
   *
   * @param validation A `scalaz.Validation` object to try converting to a `Validationable`
   *
   * @see org.scalatest.OptionValues.Valuable
   */
  class Validationable[E, T](validation: Validation[E, T], pos: source.Position) {
    def value: T = {
      validation match {
        case Success(right) =>
          right
        case Failure(left) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"$left is Failure, expected Success."), None, pos)
      }
    }

    /**
     * Allow .leftValue on an Validation to extract the left side. Like .value, but for the left.
     */
    def leftValue: E = {
      validation match {
        case Success(right) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"$right is Success, expected Failure."), None, pos)
        case Failure(left) =>
          left
      }
    }
  }

}

// TODO: Fix me as a proper repl session example
/**
 *
 * Companion object for easy importing – rather than mixing in – to allow `ValidationValues` operations.
 *
 * This will permit you to invoke a `value` method on an instance of a `scalaz.Validation`,
 * which attempts to unwrap the right validation
 *
 * Similar to `org.scalatest.OptionValues.Valuable`
 *
 * Meant to allow you to make statements like:
 *
 * <pre class="stREPL">
 *   result.value should be &gt; 15
 * </pre>
 *
 * Where it only matches if result is `Success(9)`
 *
 * Otherwise your test will fail, indicating that it was left instead of right
 *
 * @see org.scalatest.OptionValues.Valuable
 */
object ValidationValues extends ValidationValues
