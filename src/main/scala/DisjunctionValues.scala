package org.typelevel.scalatest

import org.scalatest.exceptions.{ TestFailedException, StackDepthException }

import scalaz.{ -\/, \/, \/- }
import scalaz.syntax.either._
import org.scalactic.source

trait DisjunctionValues {
  import scala.language.implicitConversions

  /**
   * Implicit conversion that adds a `value` method to `scalaz.\/`
   *
   * @param disjunction the `scalaz.\/` on which to add the `value` method
   */
  implicit def convertDisjunctionToDisjunctionated[E, T](disjunction: E \/ T)(implicit pos: source.Position) = new Disjunctionable(disjunction, pos)

  // TODO: Fix me as a proper repl session example
  /**
   * Container class for matching success
   * type stuff in `scalaz.\/` containers,
   * similar to `org.scalatest.OptionValues.Valuable`
   *
   * Meant to allow you to make statements like:
   *
   * <pre class="stREPL">
   *   result.value should be &gt; 15
   * </pre>
   *
   * Where it only matches if result is `\/-(9)`
   *
   * Otherwise your test will fail, indicating that it was left instead of right
   *
   * @param disjunction A `scalaz.\/` object to try converting to a `Disjunctionable`
   *
   * @see org.scalatest.OptionValues.Valuable
   */
  class Disjunctionable[E, T](disjunction: E \/ T, pos: source.Position) {
    def value: T = {
      disjunction match {
        case \/-(right) =>
          right
        case -\/(left) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"$left is -\\/, expected \\/-."), None, pos)
      }
    }

    /**
     * Allow .leftValue on an \/ to extract the left side. Like .value, but for the left.
     */
    def leftValue: E = {
      disjunction match {
        case \/-(right) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"$right is \\/-, expected -\\/."), None, pos)
        case -\/(left) =>
          left
      }
    }
  }

}

// TODO: Fix me as a proper repl session example
/**
 *
 * Companion object for easy importing – rather than mixing in – to allow `DisjunctionValues` operations.
 *
 * This will permit you to invoke a `value` method on an instance of a `scalaz.\/`,
 * which attempts to unwrap the right disjunction
 *
 * Similar to `org.scalatest.OptionValues.Valuable`
 *
 * Meant to allow you to make statements like:
 *
 * <pre class="stREPL">
 *   result.value should be &gt; 15
 * </pre>
 *
 * Where it only matches if result is `\/-(9)`
 *
 * Otherwise your test will fail, indicating that it was left instead of right
 *
 * @see org.scalatest.OptionValues.Valuable
 */
object DisjunctionValues extends DisjunctionValues
